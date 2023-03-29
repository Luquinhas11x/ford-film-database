package com.workshop.filmsApi.controller;

import com.workshop.filmsApi.dto.DadosTokenJWTDto;
import com.workshop.filmsApi.entity.UserEntity;
import com.workshop.filmsApi.request.UserRequest;
import com.workshop.filmsApi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody UserRequest data){
        var authenticationTokentoken = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
       var authentication =  manager.authenticate(authenticationTokentoken);
       var tokenJWT  = tokenService.generateToken((UserEntity) authentication.getPrincipal());

       return ResponseEntity.ok(new DadosTokenJWTDto(tokenJWT));
    }
}
