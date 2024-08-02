package com.pichincha.client.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ClientTest {

	@Test
	void testGettersAndSetters() {
		Client client = new Client();
		String identification="1803861937";
		client.setIdentification(identification);
		assertEquals(identification, client.getIdentification(), "Identification  test failed");

		String password = "password";
		client.setPassword(password);
		assertEquals(password, client.getPassword(), "Password  test failed");

	}
}
