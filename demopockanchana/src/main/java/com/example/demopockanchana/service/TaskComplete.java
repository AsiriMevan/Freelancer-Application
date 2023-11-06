package com.example.demopockanchana.service;

import com.example.demopockanchana.dto.PaymentDto;
//import com.wf.processemc.dto.PaymentDto;

public interface TaskComplete {
    void startProcessByDefinitionKey(String processDefinitionKey, PaymentDto paymentDto) ;
}
