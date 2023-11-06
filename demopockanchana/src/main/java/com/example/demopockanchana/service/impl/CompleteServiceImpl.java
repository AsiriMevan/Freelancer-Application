package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
import com.example.demopockanchana.service.CompleteProcess;
//import com.wf.processemc.exception.EcoException;
//import com.wf.processemc.service.CompleteProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletionException;

@Service
public class CompleteServiceImpl implements CompleteProcess {

    private final Logger LOGGER = LoggerFactory.getLogger(CompleteServiceImpl.class);

        @Value("${camunda.task.complete.url}")
        private String endpoint;
        @Autowired
        private RestTemplate restTemplate;

        @Override
        public void completeTaskById(String taskId, String traceId)
                throws ControlException {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Basic ZGVtbzpkZW1v");
            String requestUrl = endpoint.replace("#taskId#", taskId);

            restTemplate.postForEntity(requestUrl, new HttpEntity<String>(headers), ProcessDefinitionResponse.class).getBody();

        }


}
