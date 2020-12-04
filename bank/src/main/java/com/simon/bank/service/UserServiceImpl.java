package com.simon.bank.service;

import com.simon.bank.domain.Account;
import com.simon.bank.domain.Transaction;
import com.simon.bank.domain.User;
import com.simon.bank.dto.PointsBalanceDto;
import com.simon.bank.repository.UserRepository;
import com.simon.bank.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

   // @Autowired
    //private ModelMapper modelMapper=new ModelMapper();

    @Override
    public String addPoints(String payer, int points,  Integer userID) {
       User user= userRepository.findById(userID).orElse(new User(userID,new Account(0)));
       Account account=user.getAccount();
       int previousBalance=account.getBalance();

        account.setBalance((previousBalance+points));
        if(points<0){
            for(Transaction t: account.getTransactions()){
                if(t.getName().equals(payer)){
                    t.setPoints(t.getPoints()+points);
                    break;
                }
            }
        }

        else if(points>=0){
            Transaction transaction=new Transaction(payer, points, LocalDateTime.now());
            account.addTransaction(transaction);
        }

        userRepository.save(user);
        return "Successfully updated";
    }



    @Override
    public List<TransactionDto> deduct(int deductPoints, int userId) {
        User user= userRepository.findById(userId).orElse(null);

        if(user==null)
            return null;

        Account account=user.getAccount();
        int balance=account.getBalance();
        List<Transaction> transactions=account.getTransactions();
        List<TransactionDto> transactionDtos=new ArrayList<>();

        for(Transaction t: transactions){
            if(deductPoints==0)
                break;
            if(t.getPoints()<deductPoints && t.getPoints()>0){
                TransactionDto transactionDto=new TransactionDto();
                deductPoints-=t.getPoints();
                balance-=t.getPoints();

                transactionDto.setPoints(-t.getPoints());
                transactionDto.setName(t.getName());
                transactionDto.setTransactionDate(LocalDateTime.now());

                transactionDtos.add(transactionDto);

                t.setPoints(0);
            }

            else if(t.getPoints()>deductPoints){
                TransactionDto transactionDto=new TransactionDto();
                t.setPoints(t.getPoints()-deductPoints);
                balance-=balance-deductPoints;
                transactionDto.setPoints(-deductPoints);
                transactionDto.setName(t.getName());
                transactionDto.setTransactionDate(LocalDateTime.now());
                transactionDtos.add(transactionDto);
                deductPoints=0;
            }
        }

        userRepository.save(user);
        return transactionDtos;
    }


    @Override
    public List<PointsBalanceDto> getUserPointsBalance(Integer userId) {
        User user= userRepository.findById(userId).orElse(null);

        if(user==null)
            return null;

        Account account=user.getAccount();
        List<PointsBalanceDto> pointsBalanceDtos=new ArrayList<>();
        Map<String, Integer> payers=new HashMap<>();

        account.getTransactions().forEach(t->{
            String name=t.getName();
            Integer point=payers.get(t.getName());
            if(point==null){
                payers.put(t.getName(),t.getPoints());
            }
            else if(point!=null){
                payers.put(name,(point+t.getPoints()));
            }


        });


        payers.forEach((name,point)->{
            PointsBalanceDto pointsBalanceDto=new PointsBalanceDto();
            pointsBalanceDto.setName(name);
            pointsBalanceDto.setPoints(point);
            pointsBalanceDtos.add(pointsBalanceDto);
        });

               //PointsBalanceDto.add(modelMapper.map(t, TransactionDto.class));

        return pointsBalanceDtos;
    }

}
