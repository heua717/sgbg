package com.sgbg.blockchain.service;

import com.sgbg.blockchain.common.exception.NoWalletException;
import com.sgbg.blockchain.common.exception.WrongPasswordException;
import com.sgbg.blockchain.domain.WalletHistory;
import com.sgbg.blockchain.repository.WalletHistoryRepository;
import com.sgbg.blockchain.repository.WalletRepository;
import com.sgbg.blockchain.service.interfaces.IWalletService;
import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.blockchain.wrapper.Cash_sol_Cash;
import com.sgbg.blockchain.wrapper.Contracts_SingleBungle_sol_SingleBungle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class WalletService implements IWalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    Web3j web3j;

    @Value("${eth.cash.contract}")
    String cashContractAddress;

    @Value("${eth.admin.address}")
    String admin;

    @Value("${eth.admin.private}")
    String adminPrivateKey;

    @Autowired
    WalletHistoryRepository walletHistoryRepository;
    // 지갑이 사용되는 매 이벤트마다 walletHistory에 넣는다.

    @Override
    public Wallet createWallet(long userId, String password) throws Exception{
        // 프라이빗 네트워크에 사용할 지갑을 등록
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        String privateKey = ecKeyPair.getPrivateKey().toString(16);
        String publicKey = ecKeyPair.getPublicKey().toString(16);


        WalletFile walletFile = org.web3j.crypto.Wallet.createLight(password, ecKeyPair);
        String address = walletFile.getAddress();

        Wallet wallet = Wallet.builder()
                .ownerId(userId)
                .password(password)
                .publicKey(publicKey)
                .privateKey(privateKey)
                .address(address)
                .build();
        walletRepository.save(wallet);

        // --------------- 개발 하면 지울 부분 -------------------
        // Credentials 테스트
        Credentials credentials = Credentials.create(ecKeyPair);
        Credentials credentials1 = Credentials.create(privateKey, publicKey);
        if(credentials1.getAddress().equals(credentials.getAddress())){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.println("========== 지갑 생성=========");
        System.out.println(privateKey);
        System.out.println(address); // 지갑 address
        System.out.println(credentials.getAddress());
        System.out.println("===================");
        // --------------- 개발 하면 지울 부분 end -------------------

        return wallet;
    }

    @Override
    public Wallet charge (long userId, long money) throws Exception {

        Wallet wallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(wallet == null){
            throw new NoWalletException();
        }

        long moneyBeforeTransaction = wallet.getCash();
        String privateKey = wallet.getPrivateKey();
        String publicKey = wallet.getPublicKey();
        Credentials credentials = Credentials.create(privateKey, publicKey);
        String address = credentials.getAddress();
        System.out.println(address);

        // -------------- 스마트 컨트랙트 함수 ---------------
        // 지갑 address와 money를 통해 우리가 만든 토큰을 충전한다.
        Credentials credentialsAdmin = Credentials.create(adminPrivateKey);
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Cash_sol_Cash cashContract = Cash_sol_Cash.load(cashContractAddress, web3j, credentialsAdmin, contractGasProvider);

        cashContract.approve(admin, BigInteger.valueOf(money)).send();
        TransactionReceipt receipt = cashContract.transferFrom(admin, address, BigInteger.valueOf(money)).send();
        System.out.println("컨트랙트 주소: " + cashContract.getContractAddress());
        System.out.println("충전 balance: " + cashContract.balanceOf(address).send());
        System.out.println("admin balance: "+ cashContract.balanceOf(admin).send());
        System.out.println("receipt: "+ receipt.getTransactionHash());

        // TransactionReceipt를 가지고 Transaction 엔티티만들어서 저장한다.

        // -----------------------------------------------

        // 스마트 컨트랙트를 사용하여 돈을 충전했다면 db에도 반영시킨다.
        wallet.setCash(moneyBeforeTransaction+money);

        // 또한 walletHistory에 type=charge로 생성하여 추가한다.
        WalletHistory walletHistory = WalletHistory.builder()
                .wallet(wallet)
                .totalMoneyBeforeTransaction(moneyBeforeTransaction)
                .money(money)
                .createdAt(LocalDateTime.now())
                .type("charge")
                .build();
        walletHistoryRepository.save(walletHistory);

        return wallet;
    }

    @Override
    public void checkWallet(long userId) throws Exception {
        // 지갑이 있는지 체크한다.
        // 지갑이 없는 경우엔 NoWalletException을 발생시킨다.
        // get과 나눈 이유는 프론트에서 처음 화면에서 비밀번호를 띄울지 지갑생성을 띄울지 미리 알아야 하기 때문이다.
        // get에서는 password를 받고 있기 때문에 둘을 나눴다.
        Wallet wallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(wallet == null){
            throw new NoWalletException();
        }
    }

    @Override
    public Wallet getWallet(long userId, String password) throws Exception {

        Wallet wallet = walletRepository.findByOwnerIdAndPassword(userId, password).orElse(null);
        if(wallet == null){
            throw new WrongPasswordException();
        }
        return wallet;
    }

    @Override
    public String createRoom(long hostId, long duration, long minimumAmount) throws Exception {

        // 방장이 방을 만들고 SingleBungle 스마트 컨트랙트를 생성하는 함수
        Wallet wallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(wallet == null){
            throw new NoWalletException();
        }

        Credentials credentials = Credentials.create(wallet.getPrivateKey(), wallet.getPublicKey());
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.deploy(web3j, credentials, contractGasProvider, cashContractAddress, credentials.getAddress(), BigInteger.valueOf(duration), BigInteger.valueOf(minimumAmount)).send();

        // contract 로부터 transactionReceipt를 받아와서 transaction 저장
        TransactionReceipt receipt = contract.getTransactionReceipt().orElse(null);
        //-----------------------------------------------------------

        return contract.getContractAddress(); // 방금 생성한 싱글벙글 스마트 컨트랙트 주소 반환
        // room에서는 이 컨트랙트 주소를 room에서 저장해야 한다.

    }

    @Override
    public Wallet enterRoom (long userId, long hostId, String sgbgContractAddress, long money) throws Exception {

        // room 쪽에서 wallet api를 콜하는 게 맞는 것 같다.
        // roomApi에서 가져온 hostId를 사용하여 hostWallet을 구한다.
        // 이곳에서는 room을 조회하지 않는다. room에서 요청한 사항만을 전달할 뿐이다.

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);

        Wallet userWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(userWallet == null){
            throw new NoWalletException();
        }

        String userPrivateKey = userWallet.getPrivateKey();
        String userPublicKey = userWallet.getPublicKey();
        Credentials userCredentials = Credentials.create(userPrivateKey, userPublicKey);
        String userAddress = userCredentials.getAddress();

        // -------------- 스마트 컨트랙트 함수 ------------------
        // 주어진 값 : host 지갑 address, 유저 지갑 address, 지불할 금액(money)
        // 지갑 address를 가지고 private network에 가서 작업을 수행하도록 스마트 컨트랙트 실행
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
        TransactionReceipt receipt = contract.enterRoom(userAddress, BigInteger.valueOf(money)).send();

        // transactionReceipt를 통해 엔티티 저장

        // -------------- 스마트 컨트랙트 끝 --------------------

        // 스마트 컨트랙트에서 돈을 모았다가 모임이 성사되면 모인돈을 한번에 호스트에게 주는 방식
        WalletHistory userWalletHistory = WalletHistory.builder()
                .wallet(userWallet)
                .totalMoneyBeforeTransaction(userWallet.getCash())
                .money(money)
                .createdAt(LocalDateTime.now())
                .type("enter")
                .build();
        walletHistoryRepository.save(userWalletHistory);

        userWallet.setCash(userWallet.getCash()-money);

        return userWallet; // 반환값을 어떻게 할지는 의논 후 결정
    }

    @Override
    public Wallet exitRoom (long userId, long hostId, String sgbgContractAddress, long money) throws Exception {

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);

        Wallet userWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(userWallet == null){
            throw new NoWalletException();
        }

        String userPrivateKey = userWallet.getPrivateKey();
        String userPublicKey = userWallet.getPublicKey();
        Credentials userCredentials = Credentials.create(userPrivateKey, userPublicKey);
        String userAddress = userCredentials.getAddress();


        // -------------- 스마트 컨트랙트 함수 ------------------
        // 주어진 값 : host 지갑 address, 유저 지갑 address, 지불할 금액(money)
        // 방을 다시 나가므로 지불했던 돈 다시 반환받기
        // 지갑 address를 가지고 private network에 가서 작업을 수행하도록 스마트 컨트랙트 실행
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
        TransactionReceipt receipt = contract.leaveRoom(userAddress, BigInteger.valueOf(money)).send();

        // transaction 엔티티 저장

        // -------------- 스마트 컨트랙트 끝 --------------------

        WalletHistory userWalletHistory = WalletHistory.builder()
                .wallet(userWallet)
                .totalMoneyBeforeTransaction(userWallet.getCash())
                .money(money)
                .createdAt(LocalDateTime.now())
                .type("exit")
                .build();
        walletHistoryRepository.save(userWalletHistory);

        userWallet.setCash(userWallet.getCash()+money);

        return userWallet; // 반환값을 어떻게 할지는 의논 후 결정
    }

    @Override
    public Wallet endRoom(long roomId, long hostId, String sgbgContractAddress) throws Exception {
        // 모집이 끝났을 때 방장에게 모아둔 돈을 주는 메서드


        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // 방장의 지갑 주소
        // 스마트 컨트랙트를 사용하여 방 모임에 대한 모집 끝남을 알린다.
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Credentials credentialsAdmin = Credentials.create(adminPrivateKey);
        Cash_sol_Cash cashContract = Cash_sol_Cash.load(cashContractAddress, web3j, credentialsAdmin, contractGasProvider);
        BigInteger beforeWithdraw = cashContract.balanceOf(hostAddress).send();

        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
        TransactionReceipt receipt = contract.withdraw(hostAddress).send();
        // transaction 엔티티 저장

        BigInteger afterWithdraw = cashContract.balanceOf(hostAddress).send();
        long withdrawMoney = afterWithdraw.longValue() - beforeWithdraw.longValue();

        // 모임에서 모인 돈을 계산하여 hostWallet.setCash()를 해준다.
        hostWallet.setCash(afterWithdraw.longValue());

        WalletHistory hostWalletHistory = WalletHistory.builder()
                .wallet(hostWallet)
                .totalMoneyBeforeTransaction(hostWallet.getCash())
                .money(withdrawMoney)
                .createdAt(LocalDateTime.now())
                .type("end")
                .build();
        walletHistoryRepository.save(hostWalletHistory);

        return hostWallet;
    }

    @Override
    public List<WalletHistory> getHistoryList(long userId) throws Exception {
        // userId를 통해 walletId를 구한다.
        // walletId를 통해 List<WalletHistory>를 구한다.

        Wallet wallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(wallet == null){
            throw new NoWalletException();
        }

        return walletHistoryRepository.findAllByWallet(wallet);
    }


}
