package com.revature.marstown.dtos.responses;

import java.util.HashSet;
import java.util.Set;

import com.revature.marstown.entities.MenuItem;
import com.revature.marstown.entities.MenuSection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemResponse {
    private String id;
    private String name;
    private String description;
    private Integer displayOrder;
    private String imageUrl;
    private Set<MenuSectionResponse> menuSections;

    public MenuItemResponse(MenuItem menuItem) {
        this.id = menuItem.getId();
        this.name = menuItem.getItem().getName();
        this.description = menuItem.getItem().getDescription();
        this.displayOrder = menuItem.getDisplayOrder();
        this.imageUrl = menuItem.getItem().getImageUrl();
        if (menuItem.getMenuSections() != null) {
            this.menuSections = new HashSet<>();
            for (MenuSection menuSection : menuItem.getMenuSections()) {
                this.menuSections.add(new MenuSectionResponse(menuSection));
            }
        }
    }
}
