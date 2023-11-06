package com.example.demopockanchana.controller;

import com.example.demopockanchana.dto.CommonResponsePayloadDto;
import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
import com.example.demopockanchana.service.ProcessPay;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api/startProcess")
public class StartEvaluationProcessController {

    @Autowired
    private ProcessPay service;
    private static final Logger LOGGER = LoggerFactory.getLogger(StartEvaluationProcessController.class);

    @PostMapping("/key/{process-definition-key}/start")
    @ApiOperation(value = "Create a process instance with process definition key", notes = "Create a process instance with process definition key of the workflow.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @ApiImplicitParams({})
    public CommonResponsePayloadDto createNewProcessInstanceByDefinitionKey(
            @PathVariable("process-definition-key") String processDefinitionKey, @RequestBody PaymentDto paymentDto) {

       ProcessDefinitionResponse definitionResponse =  service.startProcessByDefinitionKey(processDefinitionKey);

        LOGGER.info("createNewProcessInstanceByDefinitionKeyResponse : processDefinitionKey={}", processDefinitionKey);

        CommonResponsePayloadDto responsePayload = new CommonResponsePayloadDto();

        String message = String.format("Successfully Starts the Instance with  TaskId");

        responsePayload.setSuccess(true);
        responsePayload.setMessage(message);
        responsePayload.setCode(200);
        responsePayload.setData(definitionResponse);
        return responsePayload ;
    }
}

