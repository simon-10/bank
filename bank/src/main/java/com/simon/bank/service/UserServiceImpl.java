package com.simon.bank.service;

import com.simon.bank.domain.Account;
import com.simon.bank.domain.Transaction;
import com.simon.bank.domain.User;
import com.simon.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addPoints(String payer, int points,  Integer userID) {

        User user=userRepository.getOne(userID);
        if(user==null){
            user=new User(userID,new Account(0));
            return "No such user!";
        }
        Transaction transaction=new Transaction(payer, points, LocalDate.now());
        user.getAccount().addTransaction(transaction);

        userRepository.save(user);



        return "Successfully updated";
    }
}
