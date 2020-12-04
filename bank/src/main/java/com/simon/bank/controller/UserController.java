package com.simon.bank.controller;

import com.simon.bank.domain.Transaction;
import com.simon.bank.dto.PointsBalanceDto;
import com.simon.bank.service.UserService;
import com.simon.bank.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "home page";
    }


    @GetMapping("/addpoint/{payer}/{points}/{userId}")
    public String addPoints (@PathVariable String payer, @PathVariable int points, @PathVariable Integer userId){
        return  userService.addPoints(payer, points, userId);
    }


    @GetMapping("/deduct/{deductPoints}/{userId}")
    public List<TransactionDto>  deduct(@PathVariable int deductPoints, @PathVariable Integer userId){
        return  userService.deduct(deductPoints, userId);
    }

    @GetMapping("/userpoints")
    public List<PointsBalanceDto> getUserPointsBalance(@RequestParam Integer id){
        return userService.getUserPointsBalance(id);
    }
}
