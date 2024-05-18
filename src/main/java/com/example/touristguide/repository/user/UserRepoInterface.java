package com.example.touristguide.repository.user;

import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserTableDto;

import java.util.List;

public interface UserRepoInterface {
    User findUserByLogin(UserLoginDto userLoginDto);
    List<UserTableDto> getAllUsers();

    void addUser(CreateUserDto createUserDto);
}
