//package com.sgbg.service;
//
//import com.sgbg.Application;
//import com.sgbg.blockchain.domain.factory.Wallet;
//import com.sgbg.service.interfaces.IWalletService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class WalletServiceTest
//{
//	@Autowired
//	private IWalletService walletService;
//	private static String testAddress = "fakeaddress00";
//	private static String tempAddress = "fakeaddress01";
//
//	@Transactional
//	@Test
//	public void testRegister(){
//		Wallet w = new Wallet();
//		w.setAddress(tempAddress);
//		w.setOwnerId(15);
//		w.setBalance(BigDecimal.valueOf(1000));
//
//		Wallet walletRegistered = this.walletService.register(w);
//
//		assert walletRegistered != null;
//		assert walletRegistered.getOwnerId() == w.getOwnerId();
//		assert walletRegistered.getAddress().equals(tempAddress);
//	}
//
//
//	@Transactional
//	@Test
//	public void testSyncBalance(){
//		Wallet w = this.walletService.syncBalance(testAddress, BigDecimal.valueOf(5000), 0);
//
//		assert w != null;
//		assert w.getAddress().equals(testAddress);
//		assert w.getBalance().compareTo(new BigDecimal(5000)) == 0;
//	}
//
//}
