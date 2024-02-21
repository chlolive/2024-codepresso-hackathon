package com.codepresso.sns.controller;

import com.codepresso.sns.dto.SignInDTO;
import com.codepresso.sns.dto.SignUpDTO;
import com.codepresso.sns.dto.UserDTO;
import com.codepresso.sns.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return userService.getUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //2.로그인
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<UserDTO> signIn(@Valid @RequestBody SignInDTO signInDTO) {
        long userId = userService.signIn(signInDTO);
        if (userId == 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok().header(SET_COOKIE,
                generateUidCookie(userId).toString()).build();
    }

    private ResponseCookie generateUidCookie(long userId) {
        return ResponseCookie.from("uid",
                String.valueOf(userId)).build();
    }

    //3.회원정보 간단조회
    @GetMapping("/{userId}/summary")
    public ResponseEntity<UserDTO> search(@PathVariable long userId) {
        return userService.getUserById(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
