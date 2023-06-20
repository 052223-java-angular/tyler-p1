package com.revature.marstown.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.FavoriteMenuItemOffer;

@Repository
public interface FavoriteMenuItemOfferRepository extends JpaRepository<FavoriteMenuItemOffer, String> {
    Optional<FavoriteMenuItemOffer> findByUserIdAndMenuItemOfferId(String userId, String menuItemOfferId);

    List<FavoriteMenuItemOffer> findByUserId(String userId);
}
