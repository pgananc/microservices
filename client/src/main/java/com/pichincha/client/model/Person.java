package com.pichincha.client.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String name;
    private String genre;
    private Integer age;
    private String identification;
    private String address;
    private String phone;

    
}
