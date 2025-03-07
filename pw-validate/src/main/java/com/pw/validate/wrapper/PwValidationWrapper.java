package com.pw.validate.wrapper;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PwValidationWrapper {

    public static String wrapper(BindingResult result){
        if(Optional.ofNullable(result).isPresent()){
            List<ObjectError> errors = result.getAllErrors();
            return errors.stream().map(item -> item.getDefaultMessage()).collect(Collectors.joining(";"));
        }
        return "";
    }

}
