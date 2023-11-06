package com.example.demopockanchana.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "freelancer_applicant_status")
@Data
public class FreelancerApplicantStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "process_id")
    private Integer processId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "status")
    private String status;
}
