package com.web.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.account.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
