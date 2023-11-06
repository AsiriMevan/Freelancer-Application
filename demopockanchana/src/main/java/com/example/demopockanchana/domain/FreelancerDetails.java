package com.example.demopockanchana.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "freelancer_details")
@Data
public class FreelancerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "preferred_language")
    private String language;


}
