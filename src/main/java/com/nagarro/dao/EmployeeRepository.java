package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Employees;

public interface EmployeeRepository extends JpaRepository<Employees , Integer>  {

}
