package com.revature.marstown.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "menu_item_offers")
public class MenuItemOffer {
    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "price_currency", nullable = false)
    private String priceCurrency;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    @JsonBackReference
    private MenuItem menuItem;

    @Column(name = "max_quantity")
    private Integer minimumQuantity;

    @Column(name = "min_quantity")
    private Integer maximumQuantity;
}
