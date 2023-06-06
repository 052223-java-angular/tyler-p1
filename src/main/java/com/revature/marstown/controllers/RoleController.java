package com.revature.marstown.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.NewRoleRequest;
import com.revature.marstown.services.RoleService;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

/**
 * The RoleController class provides operations related to role management.
 */
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    /**
     * Creates a new role.
     *
     * @param req the NewRoleRequest object containing role creation details
     * @return ResponseEntity with the HTTP status indicating the success or failure
     *         of the role creation
     */
    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody NewRoleRequest req) {
        if (!roleService.isUniqueRole(req.getName())) {
            throw new ResourceConflictException("Role " + req.getName() + " already exists");
        }

        roleService.saveRole(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Exception handler for ResourceConflictException.
     *
     * @param e the ResourceConflictException to handle
     * @return ResponseEntity with the error message and status code indicating
     *         resource conflict
     */
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
}