package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.User;

public interface UserRepository extends JpaRepository<User , Integer> {

}
