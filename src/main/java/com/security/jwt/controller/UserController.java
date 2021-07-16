package com.security.jwt.controller;

import com.security.jwt.business.UserRules;
import com.security.jwt.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserRules userBusinessRules;

    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO login(@RequestBody() UserDTO userDTO) {
        return userBusinessRules.getToken(userDTO.getUserName(), userDTO.getPwd());
    }

}
