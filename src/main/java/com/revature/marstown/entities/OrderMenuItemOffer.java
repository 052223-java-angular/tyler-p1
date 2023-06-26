package com.revature.marstown.entities;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

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
@Table(name = "order_menu_item_offers")
public class OrderMenuItemOffer {
    @Id
    private String id;

    @Column
    Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_offer_id")
    @JsonBackReference
    private MenuItemOffer menuItemOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_order_menu_item_offer_id")
    @JsonBackReference
    private OrderMenuItemOffer parenOrderMenuItemOffer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parenOrderMenuItemOffer")
    @JsonManagedReference
    private Set<OrderMenuItemOffer> childOrderMenuItemOffers;

    public OrderMenuItemOffer(Order order, CartMenuItemOffer cartMenuItemOffer, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.quantity = cartMenuItemOffer.getQuantity();
        this.order = order;
        this.price = price;
        this.menuItemOffer = cartMenuItemOffer.getMenuItemOffer();
    }

    public OrderMenuItemOffer(Order order, CartMenuItemOffer cartMenuItemOffer, BigDecimal price,
            OrderMenuItemOffer parenOrderMenuItemOffer) {
        this(order, cartMenuItemOffer, price);
        this.parenOrderMenuItemOffer = parenOrderMenuItemOffer;
    }
}
