package com.cakefactory.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionBasketTest {

    Basket basket;

    @BeforeEach
    void setUp()
    {
        basket = new SessionBasket();
    }

    @Test
    void increasesTotal() {
        basket.add("test");
        basket.add("test");

        assertThat(basket.getTotalItems()).isEqualTo(2);
    }
}