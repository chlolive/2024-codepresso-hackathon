package com.codepresso.sns.service;

import com.codepresso.sns.dto.SignInDTO;
import com.codepresso.sns.dto.SignUpDTO;
import com.codepresso.sns.dto.UserDTO;
import com.codepresso.sns.mapper.UserMapper;
import com.codepresso.sns.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public long signUp(SignUpDTO signUpDTO) {
        return userMapper.insert(new User(signUpDTO.userName(), signUpDTO.email(),
                passwordEncoder.encode(signUpDTO.password()), signUpDTO.introduction(), signUpDTO.occupation(), signUpDTO.birthday(), signUpDTO.city()));
    }

    public long signIn(SignInDTO signInDTO) {

        return Optional.ofNullable(userMapper.findByEmail(signInDTO.email()))
                .filter(user -> passwordEncoder.matches(signInDTO.password(),
                        user.getPassword()))
                .map(User::getUserId)
                .orElse(0L);
    }
    public Optional<UserDTO> getUserById(long userId) {
        return Optional.ofNullable(userMapper.findById(userId)).map(User::toDTO);
    }


}
