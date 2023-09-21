package com.onlinebankingsystem.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebankingsystem.springproject.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByEmailID(String emailID);
}