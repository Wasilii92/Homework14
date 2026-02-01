package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private String name;


public Product(String name) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Название продукта не может быть пустым");
    }
        this.name = name;

    }

    @Override
    public String getSearchTerm() {

        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
@Override
    public String getName(){
        return name;
    }

    public abstract int getPrice();
    public abstract boolean isSpecial();
    public abstract String toString();
}
