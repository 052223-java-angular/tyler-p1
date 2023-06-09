package com.revature.marstown.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.responses.MenuResponse;
import com.revature.marstown.entities.Menu;
import com.revature.marstown.services.MenuService;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable String id) {
        Optional<Menu> menu = menuService.getMenuById(id);

        if (menu.isPresent()) {
            return ResponseEntity.ok(new MenuResponse(menu.get()));
        } else {
            throw new ResourceConflictException("Menu not found!");
        }
    }
}
