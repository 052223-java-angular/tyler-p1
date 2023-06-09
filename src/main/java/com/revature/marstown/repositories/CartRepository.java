package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}