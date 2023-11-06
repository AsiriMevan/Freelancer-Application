package com.example.demopockanchana.domain;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "evaluation_metadata")
@Data
public class EvaluationMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "type")
    private String type;

}
