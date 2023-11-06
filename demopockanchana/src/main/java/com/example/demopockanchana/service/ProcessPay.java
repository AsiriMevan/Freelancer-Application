package com.example.demopockanchana.service;

import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
//import com.wf.processemc.dto.PaymentDto;
//import model.ProcessDefinitionResponse;

public interface ProcessPay {
    ProcessDefinitionResponse startProcessByDefinitionKey(String processDefinitionKey) ;
}
