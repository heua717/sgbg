package com.sgbg.blockchain.service;

import com.sgbg.blockchain.repository.WalletRepository;
import com.sgbg.blockchain.service.interfaces.IWalletService;
import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.domain.Room;
import com.sgbg.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;


@Service
@Transactional
public class WalletService implements IWalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void create(long userId, String password) throws Exception{
        // 프라이빗 네트워크에 사용할 지갑을 등록
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        String privateKey = ecKeyPair.getPrivateKey().toString(16);
        String publicKey = ecKeyPair.getPublicKey().toString(16);


        WalletFile walletFile = org.web3j.crypto.Wallet.createLight(password, ecKeyPair);
        String address = walletFile.getAddress();

        Wallet wallet = Wallet.builder()
                        .ownerId(userId)
                        .publicKey(publicKey)
                        .privateKey(privateKey)
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
    }

    @Override
    public Wallet enterRoom (long userId, long roomId, long money) throws Exception {

        Room room = roomRepository.findByRoomId(roomId).orElse(null);
        if(room == null){
            // custom exception
            // 해커임 ㅋㅋ
            return null;
        }

        long hostId = room.getHostId();
        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            // custom exception
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // 방장의 지갑 주소



        Wallet senderWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(senderWallet == null){
            // custom exception
            // 지갑이 없으므로 지갑을 생성해주세요 라고 보내야 함
            return null;
        }

        String privateKey = senderWallet.getPrivateKey();
        String publicKey = senderWallet.getPublicKey();
        Credentials credentials = Credentials.create(privateKey, publicKey);
        String address = credentials.getAddress();


        // -------------- 스마트 컨트랙트 함수 ------------------
        // 주어진 값 : host 지갑 address, 유저 지갑 address, 지불할 금액(money)
        // 지갑 address를 가지고 private network에 가서 작업을 수행하도록 스마트 컨트랙트 실행

        // -------------- 스마트 컨트랙트 끝 --------------------

        hostWallet.setCash(hostWallet.getCash()+money); // 만약 컨트랙트에서 돈을 모으는 것이면 이 함수는 없어도 된다.
        senderWallet.setCash(senderWallet.getCash()-money); // db에 저장하는 캐시도 작게 만듦

        return null; // 반환값을 어떻게 할지는 의논 후 결정
    }

    @Override
    public Wallet exitRoom (long userId, long roomId, long money) throws Exception {

        Room room = roomRepository.findByRoomId(roomId).orElse(null);
        if(room == null){
            // custom exception
            // 해커임 ㅋㅋ
            System.out.println("room null in exitRoom");
            throw new Exception("이거 null 처리해야함");
        }
        long hostId = room.getHostId();
        Wallet hostWallet = walletRepository.findByOwnerId(hostId).orElse(null);
        if(hostWallet == null){
            // custom exception
            return null;
        }

        String hostPrivateKey = hostWallet.getPrivateKey();
        String hostPublicKey = hostWallet.getPublicKey();
        Credentials hostCredentials = Credentials.create(hostPrivateKey, hostPublicKey);
        String hostAddress = hostCredentials.getAddress(); // 방장의 지갑 주소



        Wallet senderWallet = walletRepository.findByOwnerId(userId).orElse(null);
        if(senderWallet == null){
            // custom exception
            // 지갑이 없으므로 지갑을 생성해주세요 라고 보내야 함
            return null;
        }

        String privateKey = senderWallet.getPrivateKey();
        String publicKey = senderWallet.getPublicKey();
        Credentials credentials = Credentials.create(privateKey, publicKey);
        String address = credentials.getAddress();


        // -------------- 스마트 컨트랙트 함수 ------------------
        // 주어진 값 : host 지갑 address, 유저 지갑 address, 지불할 금액(money)
        // 방을 다시 나가므로 지불했던 돈 다시 반환받기
        // 지갑 address를 가지고 private network에 가서 작업을 수행하도록 스마트 컨트랙트 실행

        // -------------- 스마트 컨트랙트 끝 --------------------

        hostWallet.setCash(hostWallet.getCash()-money); // 만약 컨트랙트에서 돈을 모으는 것이면 이 함수는 없어도 된다.
        senderWallet.setCash(senderWallet.getCash()+money); // db에 저장하는 캐시도 작게 만듦

        return null; // 반환값을 어떻게 할지는 의논 후 결정
    }

    @Override
    public Wallet endRoom(long roomId) throws Exception {
        // 모집이 끝났을 때 방장에게 모아둔 돈을 주는 메서드
        Room room = roomRepository.findByRoomId(roomId).orElse(null);
        if(room == null){
            // custom exception
            return null;
        }

        long hostId = room.getHostId();
        Wallet wallet = walletRepository.findByOwnerId(hostId).orElse(null);

        return null;
    }


}
