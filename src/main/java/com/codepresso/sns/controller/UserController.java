package com.codepresso.sns.controller;

import com.codepresso.sns.dto.SignInDTO;
import com.codepresso.sns.dto.SignUpDTO;
import com.codepresso.sns.dto.UserDTO;
import com.codepresso.sns.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseCookie;

import java.net.URI;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //1. 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        long userId = userService.signUp(signUpDTO);
        return ResponseEntity.created(URI.create("/user/" + userId + "/profile")).build();
    }

    //2.로그인
    @PostMapping("/login")
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody SignInDTO signInDTO) {
        long userId = userService.signIn(signInDTO);
        if (userId == 0) {
            return ResponseEntity.status(401).build();
        }
        //UserDTO userDTO =
        return ResponseEntity.ok().header(SET_COOKIE,
                generateUidCookie(userId).toString()).build();
    }

    private ResponseCookie generateUidCookie(long userId) {
        return ResponseCookie.from("uid",
                String.valueOf(userId)).build();
    }

    //3.회원정보 간단조회



}
