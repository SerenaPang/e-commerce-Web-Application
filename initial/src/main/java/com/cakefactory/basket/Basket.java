package com.cakefactory.basket;

public interface Basket {

    void add(String sku);

    int getTotalItems();

    Iterable<BasketItem> getItems(); 
}