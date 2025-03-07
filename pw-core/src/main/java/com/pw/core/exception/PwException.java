package com.pw.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PwException extends RuntimeException{

    private String message;

    public PwException(){
        this.message = super.getMessage();
    }

    public PwException(String message){
        this.message = message;
    }

}
