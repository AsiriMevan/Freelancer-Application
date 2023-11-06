package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.service.WorkFlowEngineClaim;
//import com.wf.processemc.exception.EcoException;
//import com.wf.processemc.service.WorkFlowEngineClaim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClaimServiceImpl implements WorkFlowEngineClaim {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimServiceImpl.class);

    @Value("${camunda.task.claim.url}")
    private String endpoint;

    @Value("${camunda.engine.rest.authorization}")
    private String basicAuthToken;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void claimNewTaskById(String taskId,String userId, String traceId ) throws ControlException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic ZGVtbzpkZW1v");

        String requestPayload = String.format("{\"userId\" : \"%s\"}", userId);

        String requestUrl = endpoint.replace("#taskId#", taskId);
        try {
            restTemplate.postForEntity(requestUrl, new HttpEntity<String>(requestPayload, headers), String.class);
        } catch(Exception e)
        {
            LOGGER.error("claimNewTaskByIdResponse : traceId={}|taskId={}|userId={} |Exception={} ", traceId, taskId,userId,e);
            throw new ControlException("300", "Failed to claim the task in camunda. Invalid or already claim task");
        }

    }
}



