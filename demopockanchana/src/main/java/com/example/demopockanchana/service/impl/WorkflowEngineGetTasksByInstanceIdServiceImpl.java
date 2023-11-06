package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.model.TaskDefinitionResponse;
import com.example.demopockanchana.service.WorkflowEngineGetTasksByInstanceIdService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wf.processemc.service.WorkflowEngineGetTasksByInstanceIdService;
//import model.TaskDefinitionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkflowEngineGetTasksByInstanceIdServiceImpl implements WorkflowEngineGetTasksByInstanceIdService {
    @Value("${camunda.tasks.process-instance.get.url}")
    private String endpoint;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<TaskDefinitionResponse> getTasks(String processInstanceId, String businessKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic ZGVtbzpkZW1v");
        String requestUrl = endpoint.replace("#instanceId#", processInstanceId).replace("#businessKey#", businessKey);
        List<Object> response = null;
        // convert to tasks list
        List<TaskDefinitionResponse> tasks = new ArrayList<>();
        response = restTemplate.exchange(requestUrl, HttpMethod.GET,new HttpEntity<String>(headers), List.class).getBody();
        response.stream().forEach( (object) -> {
            tasks.add(objectMapper.convertValue(object, TaskDefinitionResponse.class));
        });
        return tasks;
    }


}
