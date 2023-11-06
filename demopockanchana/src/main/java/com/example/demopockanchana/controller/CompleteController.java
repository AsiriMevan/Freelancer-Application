package com.example.demopockanchana.controller;

import com.example.demopockanchana.dto.CommonResponsePayloadDto;
import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
import com.example.demopockanchana.service.CompleteProcess;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Validated
@RestController
@RequestMapping("/api/approval-process-complete")
@Service
public class CompleteController {

        private final Logger LOGGER = LoggerFactory.getLogger(CompleteController.class);
        @Autowired
        private CompleteProcess employeeCompleteTaskService;
        @PostMapping("{task-id}/complete")
        @ApiOperation(value = "Complete a task with attribute updation", notes = "Complete a task and update process variables in camunda workflow.")
        @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
        @ApiImplicitParams({
                @ApiImplicitParam(value = "Trace id of this API requester", paramType = "query", name = "traceId", required = true, dataType = "string")})
        public CommonResponsePayloadDto completeTaskById(
                @PathVariable("task-id") String taskId, @RequestParam("traceId") String traceId) throws ControlException {

            LOGGER.info("completeTaskByIdRequest : traceId={}|taskId={}", traceId, taskId);
            employeeCompleteTaskService.completeTaskById(taskId, traceId);
            LOGGER.info("completeTaskByIdResponse : traceId={}|taskId={}", traceId, taskId);

            String message = String.format("Successfully Starts the Instance with  TaskId" , taskId );

            CommonResponsePayloadDto response = new CommonResponsePayloadDto();
            response.setSuccess(true);
            response.setMessage(message);
            response.setCode(200);
            return response;
        }

}
