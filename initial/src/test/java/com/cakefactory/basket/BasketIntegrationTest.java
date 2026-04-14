package com.cakefactory.basket;

import com.cakefactory.client.BrowserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BasketIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private BrowserClient client;

    @BeforeEach
    void setUp() {
        client = new BrowserClient(mockMvc);
    }

    @Test
    void addsItemsToBasket() {
        client.goToHomepage();
        client.clickAddToBasket("Red Velvet");
        client.clickAddToBasket("All Butter Croissant");

        assertThat(client.getBasketItems()).isEqualTo(2);
    }

    @Test
    void addsItemsToBasketForDifferentUsers() {
        client.goToHomepage();
        client.clickAddToBasket("Red Velvet");
        client.clickAddToBasket("Red Velvet");

        BrowserClient anotherClient = new BrowserClient(mockMvc);
        anotherClient.goToHomepage();
        anotherClient.clickAddToBasket("All Butter Croissant");

        assertThat(client.getBasketItems()).isEqualTo(2);
        assertThat(anotherClient.getBasketItems()).isEqualTo(1);
    }

}