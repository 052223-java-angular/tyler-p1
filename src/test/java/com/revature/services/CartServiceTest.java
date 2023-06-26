package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.marstown.entities.Cart;
import com.revature.marstown.repositories.CartRepository;
import com.revature.marstown.services.CartService;
import com.revature.marstown.utils.custom_exceptions.CartNotFoundException;

public class CartServiceTest {
    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUserIdSuccess() {
        String userId = UUID.randomUUID().toString();
        Cart cart = new Cart();
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        Cart cartResult = cartService.getCartByUserId(userId);
        assertEquals(cart, cartResult);
    }

    @Test
    void testFindByUserIdFailure() {
        String userId = UUID.randomUUID().toString();
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(CartNotFoundException.class, () -> cartService.getCartByUserId(userId));
    }
}
