package com.cakefactory.basket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BasketController.class)
public class BasketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Basket basket;

    @Test
    void addsItemsToBasket() throws Exception {
        String expectedSku = "rv";

        mockMvc.perform(MockMvcRequestBuilders.post("/basket").param("sku", expectedSku))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));

        verify(basket).add(expectedSku);
    }
}