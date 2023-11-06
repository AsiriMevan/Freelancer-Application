package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.service.ManagerCompleteService;
//import com.wf.processemc.dto.PaymentDto;
//import com.wf.processemc.exception.EcoException;
//import com.wf.processemc.service.ManagerCompleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskCompleteImpl implements ManagerCompleteService {
    private static final Logger LOGGER= LoggerFactory.getLogger(TaskCompleteImpl.class);

    @Value("${camunda.task.complete.url}")
    private String endpoint;
    @Autowired
    private RestTemplate restTemplate;

//    @Value("${claim-by-employee-amount}")
//    private Integer assigningToEscSeniorManagerAmount;

    @Override
    public void managerCompleteTaskById(String taskId, String traceId, PaymentDto paymentDto) throws ControlException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic ZGVtbzpkZW1v");

        String requestUrl = endpoint.replace("#taskId#", taskId);

        double processfinal = paymentDto.getAmount();


//        if (processfinal >= assigningToEscSeniorManagerAmount) {
            try {
                restTemplate.postForEntity(requestUrl, new HttpEntity<String>("{\"variables\" : { \"amount\":{\"value\" :"+processfinal+"}}}",headers), String.class);
            } catch (Exception e) {
                LOGGER.error("CompletePocess : traceId={}|taskId={}|Exception={} ", traceId, taskId, e);
                throw new ControlException("300", "Failed to claim the task in camunda. Invalid or already Compl task");
//            }
        }
    }



}


