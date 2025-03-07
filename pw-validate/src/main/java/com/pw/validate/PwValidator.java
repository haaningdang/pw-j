package com.pw.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class PwValidator extends LocalValidatorFactoryBean {

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);
    }

}
