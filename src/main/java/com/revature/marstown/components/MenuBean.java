package com.revature.marstown.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.marstown.dtos.responses.MenuResponse;
import com.revature.marstown.services.MenuService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuBean {
    @Autowired
    private MenuService menuService;
    @Autowired
    private StripePrices stripePrices;
    private MenuResponse menuResponse;

    @PostConstruct
    public void init() {
        this.menuResponse = new MenuResponse(menuService.getMenuById("menu-marstown").get());
        menuService.setMenuPrices(this.menuResponse);
    }
}
