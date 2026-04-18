package com.cakefactory.basket;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BasketModelAdvice {

    private final Basket basket;

    public BasketModelAdvice(Basket basket) {
        this.basket = basket;
    }

    @ModelAttribute("basketTotal")
    public int basketTotal() {
        return this.basket.getTotalItems();
    }
}