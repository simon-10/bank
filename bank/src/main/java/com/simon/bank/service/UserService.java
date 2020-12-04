package com.simon.bank.service;

import com.simon.bank.dto.PointsBalanceDto;
import com.simon.bank.dto.TransactionDto;

import java.util.List;

public interface  UserService {
    public String addPoints(String payer, int points, Integer userId);

    public List<TransactionDto> deduct(int points, int userId);

    public List<PointsBalanceDto> getUserPointsBalance(Integer userId);
}
