package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
import com.example.demopockanchana.model.TaskDefinitionResponse;
import com.example.demopockanchana.service.ProcessPay;
import com.example.demopockanchana.service.WorkflowEngineGetTasksByInstanceIdService;
import com.example.demopockanchana.utill.BusinessKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class PaymentProcessimpl implements ProcessPay {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${camunda.process-definition-key.start.url}")
    private String endpoint;

     @Autowired
    private WorkflowEngineGetTasksByInstanceIdService workflowEngineGetTasksByInstanceIdService;



    @Override
    public ProcessDefinitionResponse startProcessByDefinitionKey(String processDefinitionKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic ZGVtbzpkZW1v");

        String businessKeyNew = BusinessKeyGenerator.getUUID();
        String requestPayload = String.format("{\"businessKey\" : \"%s\"}", businessKeyNew);

        String requestUrl = endpoint.replace("#key#", processDefinitionKey);

        ProcessDefinitionResponse definitionResponse =restTemplate.postForEntity(requestUrl, new HttpEntity<String>(requestPayload, headers), ProcessDefinitionResponse.class).getBody();

        String processInstanceId = definitionResponse.getId();
        String businessKey = definitionResponse.getBusinessKey();

        List<TaskDefinitionResponse> taskResponses = workflowEngineGetTasksByInstanceIdService.getTasks(processInstanceId, businessKey);

        definitionResponse.setTasks(taskResponses);
        return definitionResponse;
    }
}
