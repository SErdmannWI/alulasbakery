package com.serdmannwi.practiceprograms.alulasbakery.exceptions;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;

    public ErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

