package com.study_flow.modules.user.controller;

import com.study_flow.modules.user.dtos.UserRequestPayload;
import com.study_flow.modules.user.dtos.UserResponse;
import com.study_flow.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "User register",
    description = "Method responsible for create a new uer with username, password and email")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "New user's id",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Username or email already exists"
            )
    })
    public ResponseEntity<UserResponse> createNewUser(@Valid UserRequestPayload requestPayload){
        var result = service.createNewUser(requestPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
