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
@Table(name = "menu_sections")
public class MenuSection {
    @Id
    private String id;

    @Column
    private Integer displayOrder;

    @Column(name = "max_quantity")
    private Integer maximumQuantity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMenuSection")
    @JsonManagedReference
    private Set<MenuItem> menuItems;

    @Column(name = "min_quantity")
    private Integer minimumQuantity;

    @ManyToOne
    @JoinColumn(name = "parent_menu_item_id", referencedColumnName = "id")
    @JsonBackReference
    private MenuItem parentMenuItem;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonBackReference
    private Section section;
}
