package com.revature.marstown.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "items")
public class Item {
    @Id
    private String id;

    @Column
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    @JsonManagedReference
    private Set<MenuItem> menuItems;
}
