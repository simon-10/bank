package com.simon.bank.controller;

import com.simon.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/addpoint/{payer}/{points}/{userId}")
    public String addPoints (String payer, int points, Integer userId){

        return  userService.addPoints(payer, points, userId);
    }
}
