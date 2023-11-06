package com.example.demopockanchana.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "freelancer")
@Data
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "process_id")
    private Integer processId;

    @Column(name = "question_number")
    private Integer questionNumber;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name ="Type")
    private String type;

    @Column(name = "user_name")
    private String userName;

    public Freelancer(long id, Integer questionNumber, String question, String answer, Integer processId, String type, String userName) {
        this.id = id;
        this.processId = processId;
        this.questionNumber = questionNumber;
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.userName = userName;
    }
    public Freelancer() {

    }
}
