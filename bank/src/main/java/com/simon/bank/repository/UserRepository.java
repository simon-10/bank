package com.simon.bank.repository;

import com.simon.bank.domain.Account;
import com.simon.bank.domain.Transaction;
import com.simon.bank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
}
