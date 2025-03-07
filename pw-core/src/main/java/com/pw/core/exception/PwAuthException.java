package com.pw.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PwAuthException extends RuntimeException{

    private String message;

    public PwAuthException(){
        this.message = super.getMessage();
    }

    public PwAuthException(String message){
        this.message = message;
    }
}
