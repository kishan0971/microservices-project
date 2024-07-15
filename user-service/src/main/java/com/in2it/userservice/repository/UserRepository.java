package com.in2it.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.userservice.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
