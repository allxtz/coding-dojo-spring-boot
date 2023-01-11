package com.assignment.spring.customresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class BackendException {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    public BackendException() {
        this.timestamp = new Date();
    }

    public BackendException(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }
}