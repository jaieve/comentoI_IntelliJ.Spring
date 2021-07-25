package com.example.comento.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data //getter+setter+ ...
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends RuntimeException {

    private int status;
    private Integer code;
    private String meessage;

    public ResponseException(ResponseException responseException) {
        this.status = responseException.status;
        this.code = responseException.code;
        this.meessage = responseException.meessage;
    }

    public ResponseException(int status, Integer code, String meessage) {
        this.status = status;
        this.code = code;
        this.meessage = meessage;
    }
}