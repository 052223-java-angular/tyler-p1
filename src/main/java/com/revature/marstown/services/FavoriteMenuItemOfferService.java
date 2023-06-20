package com.revature.marstown.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.marstown.entities.FavoriteMenuItemOffer;
import com.revature.marstown.entities.MenuItemOffer;
import com.revature.marstown.repositories.FavoriteMenuItemOfferRepository;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteMenuItemOfferService {
    private final FavoriteMenuItemOfferRepository favoriteMenuItemOfferRepository;
    private final UserService userService;

    public List<FavoriteMenuItemOffer> findAllByUserId(String userId) {
        if (userId == null) {
            throw new ResourceConflictException("User id is null!");
        }

        return favoriteMenuItemOfferRepository.findByUserId(userId);
    }

    public FavoriteMenuItemOffer addFavoriteMenuItemOffer(String userId, String menuItemOfferId) {
        if (userId == null) {
            throw new ResourceConflictException("User id is null!");
        }

        var existingFavoriteMenuItemOffer = findExistingFavoriteMenuItemOffer(userId, menuItemOfferId);

        if (existingFavoriteMenuItemOffer.isPresent()) {
            throw new ResourceConflictException("Menu Item already in favorites");
        }

        var menuItemOffer = new MenuItemOffer();
        menuItemOffer.setId(menuItemOfferId);

        return favoriteMenuItemOfferRepository.save(new FavoriteMenuItemOffer(
                UUID.randomUUID().toString(),
                userService.getById(userId),
                menuItemOffer));
    }

    public void removeFavoriteMenuItemOffer(String userId, String menuItemOfferId) {
        if (userId == null) {
            throw new ResourceConflictException("User id is null!");
        }

        if (menuItemOfferId == null) {
            throw new ResourceConflictException("Menu Item Offer id is null!");
        }

        var favoriteMenuItemOffer = findExistingFavoriteMenuItemOffer(userId, menuItemOfferId);

        if (favoriteMenuItemOffer.isEmpty()) {
            throw new MenuItemOfferNotFoundException("Menu Item Offer not found!");
        }

        favoriteMenuItemOfferRepository.deleteById(favoriteMenuItemOffer.get().getId());
    }

    public Optional<FavoriteMenuItemOffer> findExistingFavoriteMenuItemOffer(String userId, String menuItemOfferId) {
        return favoriteMenuItemOfferRepository.findByUserIdAndMenuItemOfferId(userId, menuItemOfferId);
    }
}
