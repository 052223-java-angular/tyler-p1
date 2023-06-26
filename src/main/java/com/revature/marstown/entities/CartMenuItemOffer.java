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
@Table(name = "cart_menu_item_offers")
public class CartMenuItemOffer {
    @Id
    private String id;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "menu_item_offer_id")
    @JsonBackReference
    private MenuItemOffer menuItemOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_cart_menu_item_offer_id")
    @JsonBackReference
    private CartMenuItemOffer parentCartMenuItemOffer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCartMenuItemOffer")
    @JsonManagedReference
    private Set<CartMenuItemOffer> childCartMenuItemOffers;
}
