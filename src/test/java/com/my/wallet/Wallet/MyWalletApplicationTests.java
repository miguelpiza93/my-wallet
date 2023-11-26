package com.my.wallet.Wallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MyWalletApplicationTests {

	@Autowired
	private MyWalletApplication myWalletApplication;

	@Test
	void contextLoads() {
		assertNotNull(myWalletApplication);
	}
}
