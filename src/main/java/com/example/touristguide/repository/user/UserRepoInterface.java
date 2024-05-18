package com.example.touristguide.repository.user;

import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.UserLoginDto;

public interface UserRepoInterface {
    User findUserByLogin(UserLoginDto userLoginDto);
}
