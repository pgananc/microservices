package com.pichincha.account.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.pichincha.account.dto.TransactionMessage;
import com.pichincha.account.model.Account;

@Service
public class TransactionMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public TransactionMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Account account) {
        TransactionMessage transactionMessage = new TransactionMessage();
        transactionMessage.setAccountNumber(account.getAccountNumber());
        transactionMessage.setClientId(account.getClientId());
        rabbitTemplate.convertAndSend("transactionQueue", transactionMessage);
    }
}
