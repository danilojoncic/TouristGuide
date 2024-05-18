package com.example.touristguide.service;

import com.example.touristguide.domain.user.Status;
import com.example.touristguide.domain.user.User;
import com.example.touristguide.dto.UserLoginDto;
import com.example.touristguide.repository.user.UserRepoInterface;

import javax.inject.Inject;

public class UserService {
    @Inject private UserRepoInterface userRepoInterface;

    /**
     * Uzima userLoginDto i od njega pravi usera uz pomoc repozitorijuma
     * zavisno od sadrzaja objekta user vraca razlicite intove kao sifre
     * @param userLoginDto dto objekat koji sadrzi email i sifru nekog korisinika koji
     * se mozda nalazi u bazi
     * @return vracam 0 ako korisnik sa datim kredencijalima nije prisutan u bazi
     * vracam 1 ako je korisnik po statusu blokiran
     * vracam 2 ako je korsinik prisutan i activan po statusu
     */
    public User login(UserLoginDto userLoginDto){
        return userRepoInterface.findUserByLogin(userLoginDto);
    }
}
