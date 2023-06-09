package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.MenuItemOffer;

@Repository
public interface MenuItemOfferRepository extends JpaRepository<MenuItemOffer, String> {
}
