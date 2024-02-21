package com.codepresso.sns.controller;

import com.codepresso.sns.dto.*;
import com.codepresso.sns.service.UserService;
import com.codepresso.sns.vo.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //1. 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        long userId = userService.signUpUser(signUpDTO);
        return userService.getUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //2.로그인
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<SignInDTO> signIn(@Valid @RequestBody SignInDTO signInDTO) {
        long userId = userService.signIn(signInDTO);
        if (userId == 0) {
            return ResponseEntity.status(401).build();
        }
        return userService.getSignInById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    private ResponseCookie generateUidCookie(long userId) {
        return ResponseCookie.from("uid",
                String.valueOf(userId)).build();
    }

    //3.회원정보 간단조회
    @GetMapping("/{userId}/summary")
    public ResponseEntity<SummaryDTO> search(@PathVariable long userId) {
        return userService.getSummaryById(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //4.회원정보 상세조회
    @GetMapping("/{userId}/detail")
    public ResponseEntity<DetailDTO> searchDetail(@PathVariable long userId) {
        return userService.getDetailById(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //5.회원정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<DetailDTO> patchInfo(@PathVariable long userId, @RequestBody User user) {
        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

        return userService.patchInformation(userId, user, updatedAt).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //6.비밀번호 수정
    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> patchPW(@PathVariable long userId, @RequestBody PasswordDTO passwordDTO) {

        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
        Optional<SignInDTO> signInDTO = userService.getSignInById(userId);
        if(signInDTO.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        String currentPW = userService.getById(userId).getPassword();
        String newPW = passwordDTO.newPassword();

        if(userService.checkPW(passwordDTO.currentPassword(), currentPW )) {

            userService.patchPW(userId, newPW, updatedAt);
            return ResponseEntity.badRequest().body("Password successfully updated.");
        } else {
            return ResponseEntity.status(400).build();
        }


    }



}
