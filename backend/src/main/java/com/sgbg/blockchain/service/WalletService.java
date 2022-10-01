package com.sgbg.blockchain.service;

import com.sgbg.blockchain.api.response.WalletHistoryListRes;
import com.sgbg.blockchain.common.exception.NoWalletException;
import com.sgbg.blockchain.common.exception.WrongPasswordException;
import com.sgbg.blockchain.domain.WalletHistory;
import com.sgbg.blockchain.repository.WalletHistoryRepository;
import com.sgbg.blockchain.repository.WalletRepository;
import com.sgbg.blockchain.service.interfaces.IWalletService;
import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.domain.Room;
import com.sgbg.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.*;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class WalletService implements IWalletService {

    @Autowired
    WalletRepository walletRepository;

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


        // -------------- 스마트 컨트랙트 함수 ---------------
        // 지갑 address와 money를 통해 우리가 만든 토큰을 충전한다.
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



        // 트랜잭션 해시값을 얻는 방법과 그것을 통해 트랜잭션receipt을 받아서 db에 저장한다.
//        Web3j web3j = Web3j.build(new HttpService());
//        web3j.ethGetTransactionReceipt();
//        RawTransaction.crea

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
    public Wallet enterRoom (long userId, long hostId, long roomId, long money) throws Exception {

        // room 쪽에서 wallet api를 콜하는 게 맞는 것 같다.
        // roomApi에서 가져온 hostId를 사용하여 hostWallet을 구한다.

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // 방장의 지갑 주소



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
    public Wallet exitRoom (long userId, long hostId, long roomId, long money) throws Exception {

//        Room room = roomRepository.findByRoomId(roomId).orElse(null);
//        if(room == null){
//            // custom exception
//            // 해커임 ㅋㅋ
//            System.out.println("room null in exitRoom");
//            throw new Exception("이거 null 처리해야함");
//        }
//        long hostId = room.getHostId();

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // 방장의 지갑 주소



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

        return null; // 반환값을 어떻게 할지는 의논 후 결정
    }

    @Override
    public Wallet endRoom(long roomId, long hostId) throws Exception {
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
        // 모임에서 모인 돈을 계산하여 hostWallet.setCash()를 해준다.
        long money = 100L;

        WalletHistory hostWalletHistory = WalletHistory.builder()
                .wallet(hostWallet)
                .totalMoneyBeforeTransaction(hostWallet.getCash())
                .money(money)
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
