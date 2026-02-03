package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket { private Map<String,List<Product> > productsMap;

    public ProductBasket(){
        {
            this.productsMap = new HashMap<>();
        }
    }
    public void addProduct(Product product) {
        productsMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }
    public int totalCost() {
        int total = 0;
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }



    public boolean productContents(String productName) {
        return productsMap.containsKey(productName) && !productsMap.get(productName).isEmpty();
    }

    public void clear() {
        productsMap.clear();
    }

    public int countSpecialProducts() {
        int specialCount = 0;
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        return specialCount;
    }

    public void basketContents() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        // Выводим каждый товар с использованием toString()
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }

        // Выводим итоговую сумму и количество специальных товаров
        System.out.println("Итого: " + totalCost());
        System.out.println("Специальных товаров: " + countSpecialProducts());
    }
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = new ArrayList<>();

        if (productsMap.containsKey(name)) {
            removedProducts = productsMap.remove(name);
        }

        return removedProducts;
    }
    }



