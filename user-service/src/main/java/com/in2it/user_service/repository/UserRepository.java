package com.in2it.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.user_service.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
