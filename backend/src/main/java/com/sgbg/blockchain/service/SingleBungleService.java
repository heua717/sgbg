package com.sgbg.blockchain.service;

import com.sgbg.blockchain.common.exception.NoWalletException;
import com.sgbg.blockchain.common.exception.NotEnoughMoneyException;
import com.sgbg.blockchain.domain.Transaction;
import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.blockchain.domain.WalletHistory;
import com.sgbg.blockchain.repository.TransactionRepository;
import com.sgbg.blockchain.repository.WalletHistoryRepository;
import com.sgbg.blockchain.repository.WalletRepository;
import com.sgbg.blockchain.service.interfaces.ISingleBungleService;
import com.sgbg.blockchain.wrapper.Contracts_Cash_sol_Cash;
import com.sgbg.blockchain.wrapper.Contracts_SingleBungle_sol_SingleBungle;
import com.sgbg.common.util.exception.NotFoundException;
import com.sgbg.domain.Room;
import com.sgbg.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class SingleBungleService implements ISingleBungleService {

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

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RoomRepository roomRepository;


    @Override
    public String createRoom(Long roomId, long hostId, long duration, long minimumAmount) throws Exception {

        // ????????? ?????? ????????? SingleBungle ????????? ??????????????? ???????????? ??????
        Wallet wallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if (wallet == null) {
            throw new NoWalletException();
        }
        long hostMoney = wallet.getCash();
        if(hostMoney < minimumAmount){
            throw new NotEnoughMoneyException();
        }

        Credentials credentials = Credentials.create(wallet.getPrivateKey(), wallet.getPublicKey());
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.deploy(web3j, credentials, contractGasProvider, cashContractAddress, credentials.getAddress(), BigInteger.valueOf(duration), BigInteger.valueOf(minimumAmount)).send();
        TransactionReceipt send = contract.sgbgApprove(contract.getContractAddress(), BigInteger.valueOf(minimumAmount)).send();

        wallet.setCash(hostMoney-minimumAmount);
        WalletHistory userWalletHistory = WalletHistory.builder()
                .wallet(wallet)
                .totalMoneyBeforeTransaction(wallet.getCash())
                .money(minimumAmount)
                .createdAt(LocalDateTime.now())
                .type("create")
                .build();
        walletHistoryRepository.save(userWalletHistory);
        // contract ????????? transactionReceipt??? ???????????? transaction ??????
        TransactionReceipt receipt = contract.getTransactionReceipt().orElse(null);
        if (receipt == null) {
            return null;
        }
        Transaction transaction = Transaction.builder()
                .roomId(roomId)
                .hash(receipt.getTransactionHash()).contractAddress(receipt.getContractAddress())
                .blockHash(receipt.getBlockHash()).blockNumber(receipt.getBlockNumber().longValue())
                .transactionIndex(receipt.getTransactionIndex().longValue())
                .from(receipt.getFrom()).to(receipt.getTo())
                .gas(receipt.getGasUsed().longValue())
                .storedAt(LocalDateTime.now()).relatedToMoney(false).build();
        transactionRepository.save(transaction);
        //-----------------------------------------------------------

        return contract.getContractAddress(); // ?????? ????????? ???????????? ????????? ???????????? ?????? ??????
        // room????????? ??? ???????????? ????????? room?????? ???????????? ??????.
    }

    @Override
    public Wallet enterRoom(Long roomId, long userId, long hostId, String sgbgContractAddress, long money) throws Exception {

        // room ????????? wallet api??? ????????? ??? ?????? ??? ??????.
        // roomApi?????? ????????? hostId??? ???????????? hostWallet??? ?????????.
        // ??????????????? room??? ???????????? ?????????. room?????? ????????? ???????????? ????????? ?????????.

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if (hostWallet == null) {
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);

        Wallet userWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if (userWallet == null) {
            throw new NoWalletException();
        }
        if(userWallet.getCash() < money) {
            return null;
            // throw new NotEnoughMoneyException;
        }

        String userPrivateKey = userWallet.getPrivateKey();
        String userPublicKey = userWallet.getPublicKey();
        Credentials userCredentials = Credentials.create(userPrivateKey, userPublicKey);
        String userAddress = userCredentials.getAddress();

        // -------------- ????????? ???????????? ?????? ------------------
        // ????????? ??? : host ?????? address, ?????? ?????? address, ????????? ??????(money)
        // ?????? address??? ????????? private network??? ?????? ????????? ??????????????? ????????? ???????????? ??????
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);

        contract.sgbgApprove(sgbgContractAddress, BigInteger.valueOf(money));
//        TransactionReceipt receipt = contract.enterRoom(userAddress, BigInteger.valueOf(money)).send();


        // transactionReceipt??? ?????? ????????? ??????
//        Transaction transaction = Transaction.builder()
//                .roomId(roomId)
//                .hash(receipt.getTransactionHash()).contractAddress(receipt.getContractAddress())
//                .blockHash(receipt.getBlockHash()).blockNumber(receipt.getBlockNumber().longValue())
//                .transactionIndex(receipt.getTransactionIndex().longValue())
//                .from(receipt.getFrom()).to(receipt.getTo())
//                .money(money).gas(receipt.getGasUsed().longValue())
//                .storedAt(LocalDateTime.now()).relatedToMoney(true).build();
//        transactionRepository.save(transaction);
        // -------------- ????????? ???????????? ??? --------------------

        // ????????? ?????????????????? ?????? ???????????? ????????? ???????????? ???????????? ????????? ??????????????? ?????? ??????
        WalletHistory userWalletHistory = WalletHistory.builder()
                .wallet(userWallet)
                .totalMoneyBeforeTransaction(userWallet.getCash())
                .money(money)
                .createdAt(LocalDateTime.now())
                .type("enter")
                .build();
        walletHistoryRepository.save(userWalletHistory);

        userWallet.setCash(userWallet.getCash() - money);

        return userWallet; // ???????????? ????????? ????????? ?????? ??? ??????
    }

    @Override
    public Wallet exitRoom(Long roomId, long userId, long hostId, String sgbgContractAddress, long money) throws Exception {

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if (hostWallet == null) {
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);

        Wallet userWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if (userWallet == null) {
            throw new NoWalletException();
        }

        String userPrivateKey = userWallet.getPrivateKey();
        String userPublicKey = userWallet.getPublicKey();
        Credentials userCredentials = Credentials.create(userPrivateKey, userPublicKey);
        String userAddress = userCredentials.getAddress();

        // -------------- ????????? ???????????? ?????? ------------------
        // ????????? ??? : host ?????? address, ?????? ?????? address, ????????? ??????(money)
        // ?????? ?????? ???????????? ???????????? ??? ?????? ????????????
        // ?????? address??? ????????? private network??? ?????? ????????? ??????????????? ????????? ???????????? ??????
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
//        TransactionReceipt receipt = contract.leaveRoom(userAddress, BigInteger.valueOf(money)).send();

        // transaction ????????? ??????
//        Transaction transaction = Transaction.builder()
//                .roomId(roomId)
//                .hash(receipt.getTransactionHash()).contractAddress(receipt.getContractAddress())
//                .blockHash(receipt.getBlockHash()).blockNumber(receipt.getBlockNumber().longValue())
//                .transactionIndex(receipt.getTransactionIndex().longValue())
//                .from(receipt.getFrom()).to(receipt.getTo())
//                .money(-money).gas(receipt.getGasUsed().longValue())
//                .storedAt(LocalDateTime.now()).relatedToMoney(true).build();
//        transactionRepository.save(transaction);
        // -------------- ????????? ???????????? ??? --------------------

        WalletHistory userWalletHistory = WalletHistory.builder()
                .wallet(userWallet)
                .totalMoneyBeforeTransaction(userWallet.getCash())
                .money(money)
                .createdAt(LocalDateTime.now())
                .type("exit")
                .build();
        walletHistoryRepository.save(userWalletHistory);

        userWallet.setCash(userWallet.getCash() + money);

        return userWallet; // ???????????? ????????? ????????? ?????? ??? ??????
    }

    @Override
    public Wallet endRoom(long roomId, long hostId, String sgbgContractAddress) throws Exception {
        // ????????? ????????? ??? ???????????? ????????? ?????? ?????? ?????????

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if (hostWallet == null) {
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // ????????? ?????? ??????
        // ????????? ??????????????? ???????????? ??? ????????? ?????? ?????? ????????? ?????????.
        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Credentials credentialsAdmin = Credentials.create(adminPrivateKey);
        Contracts_Cash_sol_Cash cashContract = Contracts_Cash_sol_Cash.load(cashContractAddress, web3j, credentialsAdmin, contractGasProvider);
//        BigInteger beforeWithdraw = cashContract.balanceOf(hostAddress).send();

        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
//        TransactionReceipt receipt = contract.withdraw(hostAddress).send();

//        BigInteger afterWithdraw = cashContract.balanceOf(hostAddress).send();
//        long withdrawMoney = afterWithdraw.longValue() - beforeWithdraw.longValue();

        // transaction ????????? ??????
//        Transaction transaction = Transaction.builder()
//                .roomId(roomId)
//                .hash(receipt.getTransactionHash()).contractAddress(receipt.getContractAddress())
//                .blockHash(receipt.getBlockHash()).blockNumber(receipt.getBlockNumber().longValue())
//                .transactionIndex(receipt.getTransactionIndex().longValue())
//                .from(receipt.getFrom()).to(receipt.getTo())
//                .money(withdrawMoney).gas(receipt.getGasUsed().longValue())
//                .storedAt(LocalDateTime.now()).relatedToMoney(true).build();
//        transactionRepository.save(transaction);

        Room room = roomRepository.findById(roomId).orElse(null);
        if(room == null) {
            throw new NotFoundException("Room not found");
        }
        long withdrawMoney = room.getPrice() * room.getMembers().size();

        // ???????????? ?????? ?????? ???????????? hostWallet.setCash()??? ?????????.
        hostWallet.setCash(hostWallet.getCash()+withdrawMoney);

        WalletHistory hostWalletHistory = WalletHistory.builder()
                .wallet(hostWallet)
                .totalMoneyBeforeTransaction(hostWallet.getCash())
                .money(withdrawMoney)
                .createdAt(LocalDateTime.now())
                .type("withdraw")
                .build();
        walletHistoryRepository.save(hostWalletHistory);

        return hostWallet;
    }

    @Override
    public Transaction isSuccess(long roomId, long userId, boolean isSuccess, long hostId, String sgbgContractAddress) throws Exception{

        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if (hostWallet == null) {
            return null;
        }
        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);

        Wallet userWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if (userWallet == null) {
            throw new NoWalletException();
        }

        String userPrivateKey = userWallet.getPrivateKey();
        String userPublicKey = userWallet.getPublicKey();
        Credentials userCredentials = Credentials.create(userPrivateKey, userPublicKey);
        String userAddress = userCredentials.getAddress();

        ContractGasProvider contractGasProvider = new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT);
        Contracts_SingleBungle_sol_SingleBungle contract = Contracts_SingleBungle_sol_SingleBungle.load(sgbgContractAddress, web3j, hostCredentials, contractGasProvider);
//        TransactionReceipt receipt = contract.isSuccess(userAddress, isSuccess).send();

        // transaction ????????? ??????
        // TODO : transaction ?????? enum?????? ????????????
        Transaction transaction = Transaction.builder()
                .roomId(roomId)
//                .hash(receipt.getTransactionHash()).contractAddress(receipt.getContractAddress())
//                .blockHash(receipt.getBlockHash()).blockNumber(receipt.getBlockNumber().longValue())
//                .transactionIndex(receipt.getTransactionIndex().longValue())
//                .from(receipt.getFrom()).to(receipt.getTo())
//                .gas(receipt.getGasUsed().longValue())
                .storedAt(LocalDateTime.now()).relatedToMoney(false).build();
        transactionRepository.save(transaction);

        return transaction;
    }

}
