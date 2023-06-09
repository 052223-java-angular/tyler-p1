package com.revature.marstown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.marstown.entities.MenuSection;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, String> {
}
