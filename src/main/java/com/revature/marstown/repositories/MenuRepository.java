package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
