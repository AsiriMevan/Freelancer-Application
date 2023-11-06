package com.example.demopockanchana.model;

import lombok.Data;

import java.util.List;
@Data
public class ProcessDefinitionResponse {

    private String id;

    private String definitionId;

    private String businessKey;

    private String tenantId;

    private boolean ended;

    private boolean suspended;

    private List<TaskDefinitionResponse> tasks;
}
