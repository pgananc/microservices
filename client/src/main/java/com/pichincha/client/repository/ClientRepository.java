package com.pichincha.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pichincha.client.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
