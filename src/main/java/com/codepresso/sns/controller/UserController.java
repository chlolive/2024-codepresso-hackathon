package com.codepresso.sns.controller;

import com.codepresso.sns.dto.user.*;
import com.codepresso.sns.mapper.UserMapper;
import com.codepresso.sns.service.UserService;
import com.codepresso.sns.vo.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseCookie;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    //1. 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Optional<UserDTO>> signUp(@RequestBody SignUpDTO signUpDTO) {
        Optional<UserDTO> newUserDTO = userService.signUpUser(signUpDTO);

//        if(userMapper.checkEmail(signUpDTO.email()).isPresent()){
//            return ResponseEntity.status(409).build();
//        }
        if(newUserDTO.isEmpty()){
            if(signUpDTO.userName()==null|| signUpDTO.email()==null || signUpDTO.introduction()==null || signUpDTO.occupation()==null || signUpDTO.birthday()==null || signUpDTO.city()==null){
                return ResponseEntity.status(400).build();
            }
            else if (signUpDTO.email()!=null){
                return ResponseEntity.status(409).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
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
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Password successfully updated."));
        } else {
            return ResponseEntity.status(400).build();
        }


    }



}
