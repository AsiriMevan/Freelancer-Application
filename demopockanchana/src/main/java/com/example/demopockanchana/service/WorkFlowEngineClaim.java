package com.example.demopockanchana.service;

import com.example.demopockanchana.exception.ControlException;
//import com.wf.processemc.exception.EcoException;

public interface WorkFlowEngineClaim {
    public void claimNewTaskById(String taskId, String userId, String traceId) throws ControlException;
}
