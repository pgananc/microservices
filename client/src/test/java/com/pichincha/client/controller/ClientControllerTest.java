package com.pichincha.client.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pichincha.client.model.Client;
import com.pichincha.client.service.ClientService;
import com.pichincha.client.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

	private static final String END_POINT = "/api/clientes";
	@Autowired
	private MockMvc mvc;
	@MockBean
	ClientService clientService;

	@Test
	void cuandoCreoclientPost_retornaCreatedclient() throws Exception {
		Client client = generateClient();

		mvc.perform(post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toBytes(client)))
				.andExpect(status().isCreated());
		verify(clientService, VerificationModeFactory.times(1)).saveClient(Mockito.any());
	}

	Client generateClient() {
		Client client = new Client();
		client.setName("Jose Lema");
		client.setGenre("F");
		client.setAge(28);
		client.setIdentification("1718529934");
		client.setAddress("Otavalo sn y principall");
		client.setPhone("098874587");
		client.setPassword("1245");
		client.setStatus(Boolean.TRUE);
		return client;
	}

}
