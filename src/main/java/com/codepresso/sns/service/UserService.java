package com.codepresso.sns.service;

import com.codepresso.sns.dto.*;
import com.codepresso.sns.mapper.UserMapper;
import com.codepresso.sns.vo.Summary;
import com.codepresso.sns.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public long signUpUser(SignUpDTO signUpDTO) {
        User newUser = new User(signUpDTO.userName(), signUpDTO.email(),
                passwordEncoder.encode(signUpDTO.password()), signUpDTO.introduction(),
                signUpDTO.occupation(), signUpDTO.birthday(), signUpDTO.city());
        newUser.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userMapper.insert(newUser);
        return newUser.getUserId();

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

    public User getById(long userId) {
        return userMapper.findById(userId);
    }

    public Optional<SignInDTO> getSignInById(long userId) {
        return Optional.ofNullable(userMapper.findById(userId)).map(User::toSignInDTO);
    }



    public Optional<SummaryDTO> getSummaryById(long userId) {
        return Optional.ofNullable(userMapper.findByIdSummary(userId)).map(Summary::toSummaryDTO);
    }

    public Optional<DetailDTO> getDetailById(long userId) {
        return Optional.ofNullable(userMapper.findById(userId)).map(User::toDetailDTO);
    }

    public Optional<DetailDTO> patchInformation(long userId, User user, Timestamp updatedAt){

        String curUserName = user.getUserName();
        String curIntroduction = user.getIntroduction();
        String curOccupation = user.getOccupation();
        String curCity = user.getCity();

        Optional<DetailDTO> pastDTO = getDetailById(userId);

        if(pastDTO.isPresent()){
            String pastUserName = pastDTO.get().userName();
            if (curUserName==null){
                curUserName =  pastUserName;
            }

            String pastIntroduction = pastDTO.get().introduction();
            if (curIntroduction==null){
                curIntroduction =  pastIntroduction;
            }

            String pastOccupation = pastDTO.get().occupation();
            if (curOccupation==null){
                curOccupation =  pastOccupation;
            }

            String pastCity = pastDTO.get().city();
            if (curCity==null){
                curCity =  pastCity;
            }
        }

        userMapper.patch(curUserName,curIntroduction, curOccupation, curCity, userId, updatedAt);
        return Optional.ofNullable(userMapper.findById(userId)).map(User::toDetailDTO);
    }

    public boolean checkPW (String newPW, String curPW){
        if(passwordEncoder.matches(newPW, curPW)){
            return true;
        }
        else{
            return false;
        }
    }

    public Optional<DetailDTO> patchPW(long userId, String newPW, Timestamp updatedAt) {

        long id = userMapper.patchPassword(userId, passwordEncoder.encode(newPW), updatedAt);
        return Optional.ofNullable(userMapper.findById(id)).map(User::toDetailDTO);
    }

}
