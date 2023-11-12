package com.ichwan.restful.service;

import com.ichwan.restful.model.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;

    public void validate(Object request){
        Set<ConstraintViolation<Object>> violations = validator.validate(request);

        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
