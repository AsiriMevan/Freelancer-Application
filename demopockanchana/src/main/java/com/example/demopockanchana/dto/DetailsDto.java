package com.example.demopockanchana.dto;

import com.example.demopockanchana.domain.Freelancer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class DetailsDto {

    private String nextQuestion;

    private Integer averagePercentage;

    private String level;

    private String comment;

    private List<Freelancer> freelancerList;

}
