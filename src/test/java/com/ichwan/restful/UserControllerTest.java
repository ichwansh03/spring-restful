package com.ichwan.restful;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichwan.restful.entity.User;
import com.ichwan.restful.model.request.LoginUserRequest;
import com.ichwan.restful.model.request.RegisterUserRequest;
import com.ichwan.restful.model.response.TokenResponse;
import com.ichwan.restful.model.response.UserResponse;
import com.ichwan.restful.model.response.WebResponse;
import com.ichwan.restful.repository.UserRepository;
import com.ichwan.restful.security.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        //sebelum ditest, pastikan tidak ada data pada DB
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("ahmad");
        request.setPassword("rahasia");
        request.setName("Ahmad Imaduddin");

        /**
         * mockMvc akan memanggil endpoint dari class UserController
         */
        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });

            Assertions.assertEquals("SUCCESS", response.getData());
        });
    }

    @Test
    void testRegisterBadRequest() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("");
        request.setPassword("");
        request.setName("");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });

            Assertions.assertNotNull(response.getError());
        });
    }

    @Test
    void testRegisterUsernameExists() throws Exception {
        User user = new User();
        user.setUsername("ichwan");
        user.setPassword(BCrypt.hashpw("password",BCrypt.gensalt()));
        user.setName("Ichwan Sholihin");
        userRepository.save(user);

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("ichwan");
        request.setPassword("password");
        request.setName("Ichwan Sholihin");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });

            Assertions.assertNotNull(response.getError());
        });
    }

    @Test
    void loginFailed() throws Exception {
        User user = new User();
        user.setName("Budi");
        user.setUsername("boedi");
        user.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt()));
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("boed");
        request.setPassword("admin123");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });
            Assertions.assertNotNull(response.getError());
        });
    }

    @Test
    void loginSuccess() throws Exception {
        User user = new User();
        user.setName("Budi");
        user.setUsername("boedi");
        user.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt()));
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("boedi");
        request.setPassword("admin123");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });
            Assertions.assertNull(response.getError());
            Assertions.assertNotNull(response.getData().getToken());

            //pastikan token sama
            User userDb = userRepository.findById("boedi").orElse(null);
            Assertions.assertNotNull(userDb);
            Assertions.assertEquals(userDb.getToken(), response.getData().getToken());
        });
    }

    @Test
    void getUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("password",BCrypt.gensalt()));
        user.setName("Test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis()+10000000000L);
        userRepository.save(user);

        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN","test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertNull(response.getError());
            Assertions.assertEquals("test", response.getData().getUsername());
            Assertions.assertEquals("Test", response.getData().getName());
        });
    }
}
