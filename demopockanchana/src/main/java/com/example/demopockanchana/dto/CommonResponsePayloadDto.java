package com.example.demopockanchana.dto;

import lombok.Data;

@Data
public class CommonResponsePayloadDto {
    private boolean success;

    private String message;

    private int code;

    private Object data;
}
