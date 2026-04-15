package com.cakefactory.basket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.cakefactory.catalog.CatalogService;

@Component
@SessionScope
class SessionBasket implements Basket {
   private final CatalogService catalogService;
    private final Map<String, Integer> items = new LinkedHashMap<>();

    SessionBasket(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    private int totalItems = 0;

    @Override
    public void add(String sku) {
        totalItems += 1;
    }

    @Override
    public int getTotalItems() {
        return this.totalItems;
    }

    @Override
     public Iterable<BasketItem> getItems() {
        List<BasketItem> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            var item = catalogService.getItemBySku(entry.getKey());
            if (item != null) {
                result.add(new BasketItem(item, entry.getValue()));
            }
        }
        return result;
    }

}