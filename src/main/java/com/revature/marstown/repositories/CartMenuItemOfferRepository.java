package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.CartMenuItemOffer;

@Repository
public interface CartMenuItemOfferRepository extends JpaRepository<CartMenuItemOffer, String> {
}
