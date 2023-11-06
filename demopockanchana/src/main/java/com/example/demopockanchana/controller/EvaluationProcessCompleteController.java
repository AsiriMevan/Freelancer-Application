package com.example.demopockanchana.controller;

import com.example.demopockanchana.dto.CommonResponsePayloadDto;
import com.example.demopockanchana.dto.PaymentDto;
import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.service.ManagerCompleteService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api/getclaim-process-complete")

public class EvaluationProcessCompleteController {

    private final Logger LOGGER = LoggerFactory.getLogger(EvaluationProcessCompleteController.class);
    @Autowired
    private ManagerCompleteService managerCompleteService;

    @PostMapping("{task-id}/complete")
    @ApiOperation(value = "Complete a task with attribute updation", notes = "Complete a task and update process variables in camunda workflow.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Trace id of this API requester", paramType = "query", name = "traceId", required = true, dataType = "string")})
    public CommonResponsePayloadDto completeTaskById(
            @PathVariable("task-id") String taskId,@RequestParam(name = "traceId") String traceId,  @RequestBody PaymentDto paymentDto ) throws ControlException {



        LOGGER.info("completeTaskByIdRequest : traceId={}|taskId={}", traceId, taskId);

        managerCompleteService.managerCompleteTaskById(taskId, traceId, paymentDto);

        LOGGER.info("completeTaskByIdResponse : traceId={}|taskId={}", traceId, taskId);


        CommonResponsePayloadDto response = new CommonResponsePayloadDto();
        response.setSuccess(true);
        response.setMessage("Completed the Process successfully");
        response.setCode(200);
        return response;
    }
}
