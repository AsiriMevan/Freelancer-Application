package com.example.demopockanchana.exception;

public enum ErrorCode {
    ERROR(100, "Error"),

    ERROR_CONVERSION(200, "Error in convertion"),

    ERROR_PROCESS_DEFINITION_CREATE_REQUEST(300, "Error in starting the process definition"),
    ERROR_PROCESS_INSTANCE_REMOVE_REQUEST(301, "Error in deleting the process instance"),
    ERROR_COMMON_WF_SERVICE_INSERT_REQUEST(302, "Error in inserting data"),
    ERROR_TASK_CLAIM_REQUEST(304, "Error in claiming the task"),
    ERROR_TASK_REASSIGN_REQUEST(305, "Error in reassigning the task"),
    ERROR_COMMON_WF_SERVICE_UPDATE_REQUEST(306, "Error in inserting data to common service"),

    ERROR_TASK_RESPONSE(400, "Error in fetching tasks"),
    ERROR_INSERT_COMMON_SERVICE_RESPONSE(401, "Error in inserting to common workflow service"),
    ERROR_PROCESS_DEFINITION_CREATE_RESPONSE(402, "Error in creating the process"),
    ERROR_TASK_COMPLETE_RESPONSE(403, "Error in completing the task"),
    ERROR_MESSAGE_DELIVER(404, "Error in sending message event");

    private int code;
    private String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
