package com.example.demopockanchana.service;

import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.model.ProcessDefinitionResponse;

public interface CompleteProcess {

    void completeTaskById(String taskId, String traceId)
            throws ControlException;
}
