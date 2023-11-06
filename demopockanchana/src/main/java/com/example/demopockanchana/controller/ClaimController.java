package com.example.demopockanchana.controller;


import com.example.demopockanchana.dto.CommonResponsePayloadDto;
import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.service.ProcessPay;
import com.example.demopockanchana.service.WorkFlowEngineClaim;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api/approval-process-claim")
@Service
public class ClaimController {

    @Autowired
    private ProcessPay service;

    private String basicAuthToken;
    @Autowired
    private WorkFlowEngineClaim workFlowEngineClaim;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimController.class);


    @PostMapping(("{task-id}/claim"))
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Trace id of this API requester", paramType = "query", name = "traceId", required = true, dataType = "string")})
    public CommonResponsePayloadDto claimTaskById(@PathVariable("task-id") String taskId,
                                                  @RequestParam("userId") String userId,
                                                  @RequestParam("traceId") String traceId) throws ControlException {

        long startTime = System.currentTimeMillis();

        LOGGER.info("claimTaskByIdRequest : traceId={}|taskId={}|userId={}", traceId, taskId, userId);
        workFlowEngineClaim.claimNewTaskById(taskId, userId, traceId);

        String message = String.format("Successfully claimed Task  =  %s to the = %s ", taskId, userId );

        CommonResponsePayloadDto responsePayload = new CommonResponsePayloadDto();

        responsePayload.setSuccess(true);
        responsePayload.setMessage(message);
        responsePayload.setCode(200);

        return responsePayload;
    }
}
