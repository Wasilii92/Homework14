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
        return productsMap.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }



    public boolean productContents(String productName) {
        return productsMap.containsKey(productName) && !productsMap.get(productName).isEmpty();
    }

    public void clear() {
        productsMap.clear();
    }

    private long getSpecialCount() {
        return productsMap.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public List<Object> basketContents() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return null;
        }

        // Выводим каждый товар с использованием toString()
        productsMap.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);


        // Выводим итоговую сумму и количество специальных товаров
         System.out.println("Итого: " + totalCost());
        System.out.println("Специальных товаров: " + getSpecialCount());
        return List.of();
    }
    public List<Product> removeProductByName(String name) {
        if (productsMap.containsKey(name)) {
            return productsMap.remove(name);
        }
        return new ArrayList<>();
    }
}




