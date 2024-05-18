package com.example.touristguide.repository.user;

import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserTableDto;
import com.example.touristguide.dto.UserUpdateDto;

import java.util.List;

public interface UserRepoInterface {
    User findUserByLogin(UserLoginDto userLoginDto);
    List<UserTableDto> getAllUsers();

    void addUser(CreateUserDto createUserDto);

    void deleteUser(int user_id);

    UserUpdateDto findUserById(int user_id);

    void editUser(int user_id,UserUpdateDto userUpdateDto);

    void changeStatus(int user_id,String tip);
}
