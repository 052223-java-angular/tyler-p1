package com.revature.marstown.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "menu_items")
public class MenuItem {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "menu_section_id")
    @JsonBackReference
    private MenuSection parentMenuSection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMenuItem")
    @JsonManagedReference
    private Set<MenuSection> menuSections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuItem")
    @JsonManagedReference
    private Set<MenuItemOption> menuItemOptions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuItem")
    @JsonManagedReference
    private Set<MenuItemOffer> menuItemOffers;

    @Column
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private Item item;
}
