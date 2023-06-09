package com.revature.marstown.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.marstown.entities.Menu;
import com.revature.marstown.repositories.MenuRepository;
import com.revature.marstown.utils.custom_exceptions.MenuNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Optional<Menu> getMenuById(String menuId) {
        menuRepository.findAll();

        Menu menu = null;
        try {
            menu = menuRepository.getReferenceById(menuId);
            if (menu.getName().equals(null)) {
                menu = null;
            }
        } catch (EntityNotFoundException e) {
            // logger.warn("Menu Entity not found");
            throw new MenuNotFoundException("Menu not found!");
        }

        if (menu == null) {
            return Optional.empty();
        } else {
            return Optional.of(menu);
        }
    }
}
