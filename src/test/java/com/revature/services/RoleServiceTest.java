package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.marstown.entities.Role;
import com.revature.marstown.repositories.RoleRepository;
import com.revature.marstown.services.RoleService;
import com.revature.marstown.utils.custom_exceptions.RoleNotFoundException;

public class RoleServiceTest {
    @Mock
    RoleRepository roleRepo;

    @InjectMocks
    RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNameSuccess() {
        String username = "found user";
        Role role = new Role();
        role.setName(username);
        when(roleRepo.findByName(username)).thenReturn(java.util.Optional.of(role));
        Role result = roleService.findByName(username);
        assertEquals(role, result);
    }

    @Test
    void testFindByNameFailure() {
        String username = "invalid user";
        when(roleRepo.findByName(username)).thenReturn(java.util.Optional.empty());
        assertThrows(RoleNotFoundException.class, () -> roleService.findByName(username));
    }
}