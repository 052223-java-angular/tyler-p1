package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
}
