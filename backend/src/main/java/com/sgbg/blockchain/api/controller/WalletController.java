package com.sgbg.blockchain.api.controller;

import com.sgbg.blockchain.api.response.BaseResponseBody;
import com.sgbg.blockchain.api.response.WalletHistoryListRes;
import com.sgbg.blockchain.api.response.WalletHistoryRes;
import com.sgbg.blockchain.api.response.WalletRes;
import com.sgbg.blockchain.common.exception.NoWalletException;
import com.sgbg.blockchain.common.exception.WrongPasswordException;
import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.blockchain.domain.WalletHistory;
import com.sgbg.blockchain.service.interfaces.IWalletService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wallet API", description = "사용자의 지갑 기능 제공")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private IWalletService walletService;

    @GetMapping
    public ResponseEntity<? extends BaseResponseBody> checkWallet() {

        // CookieUtil의 getUserIdByToken을 사용하여 userId를 받기
        long userId = 1L;

        try {
            walletService.checkWallet(userId);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(2000, "Success"));
        } catch(NoWalletException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponseBody.of(4020, "No Wallet"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> getWallet(@RequestParam String password) {

        // CookieUtil의 getUserIdByToken을 사용하여 userId를 받기
        long userId = 1L;

        try {
            Wallet wallet = walletService.getWallet(userId, password);
            return ResponseEntity.status(HttpStatus.OK).body(WalletRes.of(2000, "Success", wallet));
        } catch (WrongPasswordException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponseBody.of(4010, "Wrong Password"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<? extends BaseResponseBody> createWallet(@RequestParam String password) {

        // CookieUtil의 getUserIdByToken을 사용하여 userId를 받기
        long userId = 2L;

        Wallet wallet = null;
        try {
            wallet = walletService.createWallet(userId, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(wallet == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponseBody.of(4030, "Fail"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(WalletRes.of(2010, "Accepted", wallet));
    }

    @PostMapping("/charge")
    public ResponseEntity<? extends BaseResponseBody> charge(@RequestParam long money){

        // CookieUtil의 getUserIdByToken을 사용하여 userId를 받기
        long userId = 2L;

        try {
            Wallet wallet = walletService.charge(userId, money);
            return ResponseEntity.status(HttpStatus.OK).body(WalletRes.of(2010, "Accepted", wallet));
        } catch (NoWalletException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponseBody.of(4020, "No Wallet"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/history")
    public ResponseEntity<? extends BaseResponseBody> history(){

        // CookieUtil의 getUserIdByToken을 사용하여 userId를 받기
        long userId = 1L;

        try {
            List<WalletHistory> walletHistoryList = walletService.getHistoryList(userId);
            return ResponseEntity.status(HttpStatus.OK).body(WalletHistoryListRes.of(2000, "Accepted", walletHistoryList));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
























