package com.pichincha.client.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.pichincha.client.dto.TransactionMessage;
import com.pichincha.client.model.Client;
import com.pichincha.client.service.ClientService;

@Service
public class TransactionMessageConsumer {
    private final ClientService clientService;

    public TransactionMessageConsumer(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = "transactionQueue")
    public void consumeMessage(TransactionMessage transactionMessage){
        Client client = new Client();
        client.setPersonId(transactionMessage.getClientId());
        this.clientService.updateAccount(transactionMessage.getAccountNumber(), client.getPersonId());
    }
}