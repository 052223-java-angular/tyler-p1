package com.revature.marstown.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import com.revature.marstown.entities.Menu;
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
public class MenuResponse {
    private String id;
    private String name;
    private String disclaimer;
    private List<MenuSectionResponse> menuSections;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.disclaimer = menu.getDisclaimer();
        this.menuSections = new ArrayList<>();
        for (MenuSection menuSection : menu.getMenuSections()) {
            MenuItem parentMenuItem = menuSection.getParentMenuItem();
            if (parentMenuItem == null) {
                this.menuSections.add(new MenuSectionResponse(menuSection));
            }

        }
    }
}
