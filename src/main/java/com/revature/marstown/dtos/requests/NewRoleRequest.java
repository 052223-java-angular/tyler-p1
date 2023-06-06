package com.revature.marstown.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The NewRoleRequest class represents a request for creating a new role.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewRoleRequest {
    private String name;
}