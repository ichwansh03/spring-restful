package com.ichwan.restful.service.impl;

import com.ichwan.restful.entity.User;
import com.ichwan.restful.model.RegisterUserRequest;
import com.ichwan.restful.repository.UserRepository;
import com.ichwan.restful.security.BCrypt;
import com.ichwan.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    @Override
    public void register(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username is exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }
}
