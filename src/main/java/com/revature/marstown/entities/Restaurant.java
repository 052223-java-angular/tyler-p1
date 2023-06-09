package com.revature.marstown.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "restaurants")
public class Restaurant {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "address_locality")
    private String addressLocality;

    @Column(name = "address_region")
    private String addressRegion;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address_country")
    private String addressCountry;

    @OneToOne(mappedBy = "restaurant")
    private Service service;
}
