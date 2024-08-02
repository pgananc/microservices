package com.pichincha.client.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "client_id")
@Data
public class Client extends Person {

    private String password;

    private Boolean status;

   
}