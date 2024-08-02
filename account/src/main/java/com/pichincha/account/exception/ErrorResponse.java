package com.pichincha.account.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private int status;
    private String date;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int status, String date) {
        this.message = message;
        this.status = status;
        this.date = date;
    }

 
}