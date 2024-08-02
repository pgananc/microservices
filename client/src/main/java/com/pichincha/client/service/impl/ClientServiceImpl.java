package com.pichincha.client.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pichincha.client.clients.AccountClient;
import com.pichincha.client.model.Client;
import com.pichincha.client.repository.ClientRepository;
import com.pichincha.client.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	private final ClientRepository clientRepository;
	private final AccountClient accountClient;

	public ClientServiceImpl(ClientRepository clientRepository, AccountClient accountClient) {
		this.clientRepository = clientRepository;
		this.accountClient = accountClient;
	}

	@Override
	public List<Client> getAllClients() {
		return this.clientRepository.findAll();
	}

	@Override
	public Client saveClient(Client client) {
		return this.clientRepository.save(client);
	}

	@Override
	public boolean deleteClientById(Long id) {
		if (this.clientRepository.existsById(id)) {
			this.clientRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Client getClientById(Long id) {
		return this.clientRepository.findById(id).orElse(null);
	}

	@Override
	public void updateAccount(String accountNumber, Long clientId) {
		Client client = this.getClientById(clientId);
		if (Objects.nonNull(client)) {
			accountClient.updateClientName(accountNumber, client.getName());
		} else {
			logger.info("Client id not found {0}", clientId);
		}

	}

	@Override
	public Client updateById(Long id, Client client) {
		Client existingClient = this.getClientById(id);
		if (existingClient != null) {
			existingClient.setName(validateValue(client.getName(), existingClient.getName()));
			existingClient.setGenre(validateValue(client.getGenre(), existingClient.getGenre()));
			existingClient.setAge(validateInteger(client.getAge(), existingClient.getAge()));
			existingClient
					.setIdentification(validateValue(client.getIdentification(), existingClient.getIdentification()));
			existingClient.setAddress(validateValue(client.getAddress(), existingClient.getAddress()));
			existingClient.setPhone(validateValue(client.getPhone(), existingClient.getPhone()));
			existingClient.setPassword(validateValue(client.getPassword(), existingClient.getPassword()));
			existingClient.setStatus(validateBoolean(client.getStatus(), existingClient.getStatus()));

			return this.saveClient(existingClient);

		} else {
			return null;
		}
	}

	public String validateValue(String valueBefore, String valueAfter) {
		return valueBefore != null && !valueBefore.isEmpty() ? valueBefore : valueAfter;
	}

	public Boolean validateBoolean(Boolean valueBefore, Boolean valueAfter) {
		return valueBefore != null ? valueBefore : valueAfter;
	}

	public Integer validateInteger(Integer valueBefore, Integer valueAfter) {
		return valueBefore != null ? valueBefore : valueAfter;
	}
}
