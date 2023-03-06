package com.springboot.exam.jwtsecurity.service;

import com.springboot.exam.jwtsecurity.model.Users;
import com.springboot.exam.jwtsecurity.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService{

    private final UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }
}
