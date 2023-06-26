package com.revature.marstown.entities;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String disclaimer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @JsonManagedReference
    private Set<MenuSection> menuSections;

    @OneToOne(mappedBy = "menu")
    private Service service;
}
