package com.example.demopockanchana.service;

import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.exception.ControlException;
//import com.wf.processemc.dto.PaymentDto;
//import com.wf.processemc.exception.EcoException;

public interface ManagerCompleteService {


   public void managerCompleteTaskById(String taskId, String traceId, PaymentDto paymentDto) throws ControlException;
}
