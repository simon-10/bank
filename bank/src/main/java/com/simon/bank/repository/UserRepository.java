package com.simon.bank.repository;

import com.simon.bank.domain.Account;
import com.simon.bank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
