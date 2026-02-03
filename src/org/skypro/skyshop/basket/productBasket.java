package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class productBasket { private LinkedList<Product> products;

    public productBasket(){
        {
            this.products = new LinkedList<>();
        }
    }
    public void addProduct (Product product){
        products.add(product);
    }
    public int totalCost() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }


    public boolean productContents(String productName) {
        for (Product product : products) {
           if (product.getName().equals(productName)) {
                return true;
           }
        }
        return false;
    }
    public void clear() {
        products.clear();
    }
    public int countSpecialProducts() {
        int specialCount = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    public List<Object> basketContents() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return null;
        }

        // Выводим каждый товар с использованием toString()
        for (Product product : products) {
            System.out.println(product);
        }

        // Выводим итоговую сумму и количество специальных товаров
        System.out.println("Итого: " + totalCost());
        System.out.println("Специальных товаров: " + countSpecialProducts());
        return List.of();
    }
       public List<Product> removeProductByName(String name) {
            List<Product> removedProducts = new ArrayList<>();

            // Используем итератор для безопасного удаления во время итерации
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getName().equals(name)) {
                    removedProducts.add(product);
                    iterator.remove();
                }
            }

            return removedProducts;
        }
    }



