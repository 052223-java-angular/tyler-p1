package com.revature.marstown.services;

import org.springframework.stereotype.Service;

import com.revature.marstown.dtos.requests.NewRoleRequest;
import com.revature.marstown.entities.Role;
import com.revature.marstown.repositories.RoleRepository;
import com.revature.marstown.utils.custom_exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

/**
 * The RoleService class provides operations related to role management.
 */
@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepo;

    /**
     * Saves a new role based on the provided information.
     *
     * @param req the NewRoleRequest object containing role details
     * @return the newly created Role object
     */
    public Role saveRole(NewRoleRequest req) {
        Role newRole = new Role(req.getName());
        return roleRepo.save(newRole);
    }

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to find
     * @return the Role object with the specified name
     * @throws RoleNotFoundException if the role with the specified name is not
     *                               found
     */
    public Role findByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));
    }

    /**
     * Checks if a role with the specified name already exists.
     *
     * @param name the name to check for uniqueness
     * @return true if the role name is unique, false otherwise
     */
    public boolean isUniqueRole(String name) {
        return roleRepo.findByName(name).isEmpty();
    }
}