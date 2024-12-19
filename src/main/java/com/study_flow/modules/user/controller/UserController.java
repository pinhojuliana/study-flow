package com.study_flow.modules.user;

import com.study_flow.modules.user.dtos.UserRequestPayload;
import com.study_flow.modules.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/")
    public void createNewUser(@Valid UserRequestPayload requestPayload){
        service.createNewUser(requestPayload);
    }
}
