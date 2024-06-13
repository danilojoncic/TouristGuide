package com.example.touristguide.service;

import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.CreateUserDto;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.dto.UserTableDto;
import com.example.touristguide.dto.UserUpdateDto;
import com.example.touristguide.repository.user.UserRepoInterface;

import javax.inject.Inject;
import java.util.List;

public class UserService {
    @Inject private UserRepoInterface userRepoInterface;

    public User login(UserLoginDto userLoginDto){
        return userRepoInterface.findUserByLogin(userLoginDto);
    }

    public boolean addUser(CreateUserDto createUserDto){
        UserLoginDto userLoginDto = new UserLoginDto(createUserDto.getEmail(), createUserDto.getPassword());
        if(userRepoInterface.findUserByLogin(userLoginDto) != null){
            //vec sam pronasao nekog sa tim kredencijalima
            return false;
        }
        userRepoInterface.addUser(createUserDto);
        return true;
    }


    public List<UserTableDto> getAllUsersInTable(int page, int pageSize){
        return userRepoInterface.getAllUsers(page,pageSize);
    }

    public void delete(int user_id){
        userRepoInterface.deleteUser(user_id);
    }

    public UserUpdateDto findById(int user_id){
        return userRepoInterface.findUserById(user_id);
    }

    public void edit(int user_id,UserUpdateDto userUpdateDto){
        userRepoInterface.editUser(user_id,userUpdateDto);
    }

    public void changeStatus(int user_id, String status){
        userRepoInterface.changeStatus(user_id,status);
    }
}
