package com.example.demopockanchana.service;

import com.example.demopockanchana.model.TaskDefinitionResponse;
//import model.TaskDefinitionResponse;

import java.util.List;

public interface WorkflowEngineGetTasksByInstanceIdService {

    List<TaskDefinitionResponse> getTasks(String processInstanceId, String businessKey);

}
