package com.exemple.exotoken.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="token")
    private String token;
}
