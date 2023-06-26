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
@Table(name = "menu_item_options")
public class MenuItemOption {
    @Id
    private String id;

    @Column
    private String value;

    @Column
    private String parentOptionValue;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    @JsonBackReference
    private MenuItem menuItem;

    @Column
    private Integer displayOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuItemOption")
    @JsonManagedReference
    private Set<MenuItemOptionOffer> menuItemOptionOffers;
}
