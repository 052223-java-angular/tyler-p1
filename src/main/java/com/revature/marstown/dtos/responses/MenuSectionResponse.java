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
public class MenuSectionResponse {
    private String id;
    private Integer displayOrder;
    private Integer maximumQuantity;
    private Integer minimumQuantity;
    private String name;
    private String description;
    private String imageUrl;
    private Set<MenuItemResponse> menuItems;

    public MenuSectionResponse(MenuSection menuSection) {
        this.id = menuSection.getId();
        this.displayOrder = menuSection.getDisplayOrder();
        this.maximumQuantity = menuSection.getMaximumQuantity();
        this.minimumQuantity = menuSection.getMinimumQuantity();
        this.name = menuSection.getSection().getName();
        this.description = menuSection.getSection().getDescription();
        this.imageUrl = menuSection.getSection().getImageUrl();
        if (menuSection.getMenuItems() != null) {
            this.menuItems = new HashSet<>();
            for (MenuItem menuItem : menuSection.getMenuItems()) {
                this.menuItems.add(new MenuItemResponse(menuItem));
            }
        }
    }
}
