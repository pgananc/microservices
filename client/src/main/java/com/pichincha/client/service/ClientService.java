package com.pichincha.client.service;

import java.util.List;

import com.pichincha.client.model.Client;

public interface ClientService {

	List<Client> getAllClients();

	Client saveClient(Client client);

	boolean deleteClientById(Long id);

	Client getClientById(Long id);

	void updateAccount(String accountNumber, Long clientId);

	Client updateById(Long id, Client client);

}
