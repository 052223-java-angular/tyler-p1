package com.revature.marstown.dtos.responses;

import com.revature.marstown.entities.FavoriteMenuItemOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoritesResponse {
    private String id;
    private MenuItemResponse menuItemResponse;

    public FavoritesResponse(FavoriteMenuItemOffer favorite) {
        this.id = favorite.getId();
        this.menuItemResponse = new MenuItemResponse(favorite.getMenuItemOffer().getMenuItem());
    }
}
