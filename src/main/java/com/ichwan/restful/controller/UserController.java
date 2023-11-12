package com.ichwan.restful.controller;

import com.ichwan.restful.model.LoginUserRequest;
import com.ichwan.restful.model.RegisterUserRequest;
import com.ichwan.restful.model.TokenResponse;
import com.ichwan.restful.model.WebResponse;
import com.ichwan.restful.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Ketika endpoint dipanggil, UserController akan meminta request dari
     * method register() di interface UserService
     */
    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("SUCCESS").build();
    }

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request){
        TokenResponse tokenResponse = userService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }
}
