package com.ichwan.restful.service.userservice;

import com.ichwan.restful.entity.User;
import com.ichwan.restful.model.request.LoginUserRequest;
import com.ichwan.restful.model.request.RegisterUserRequest;
import com.ichwan.restful.model.response.TokenResponse;
import com.ichwan.restful.model.response.UserResponse;

public interface UserService {

    void register(RegisterUserRequest request);

    UserResponse get(User user);

    //untuk login user
    TokenResponse login(LoginUserRequest request);
}
