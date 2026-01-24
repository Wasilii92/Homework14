package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class productBasket { private Product[]products;
    private int count=0;
    public productBasket(){
        this.products=new Product[5];
    }
    public void addProduct (Product product){
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }
    public int totalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total+=products[i].getPrice();
        }
        return total;
    }


    public boolean productContents(String productName) {
        for (int i = 0; i < count; i++) {
           if (products[i].getName().equals(productName)) {
                return true;
           }
        }
        return false;
    }
    public void clear(){
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
    public int countSpecialProducts() {
        int specialCount = 0;
        for (int i = 0; i < count; i++) {
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    public void basketContents() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        // Выводим каждый товар с использованием toString()
        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
        }

        // Выводим итоговую сумму и количество специальных товаров
        System.out.println("Итого: " + totalCost());
        System.out.println("Специальных товаров: " + countSpecialProducts());
    }
}
