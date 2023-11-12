package com.ichwan.restful.service.userservice;

import com.ichwan.restful.model.LoginUserRequest;
import com.ichwan.restful.model.RegisterUserRequest;
import com.ichwan.restful.model.TokenResponse;

public interface UserService {

    void register(RegisterUserRequest request);

    //untuk login user
    TokenResponse login(LoginUserRequest request);
}
