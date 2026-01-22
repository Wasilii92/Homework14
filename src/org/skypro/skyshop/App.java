package org.skypro.skyshop;

import org.skypro.skyshop.basket.productBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product laptop=new Product("Ноутбук", 75000);
        Product phone = new Product("Смартфон", 35000);
        Product headphones = new Product("Наушники", 5000);
        Product tablet = new Product("Планшет", 25000);
        Product charger = new Product("Зарядное устройство", 1500);
        Product mouse = new Product("Мышь", 1200);
        productBasket basket=new productBasket();
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(charger);
        basket.addProduct(mouse);
        basket.basketContents();
        String searchName1="Смартфон";
        boolean result= basket.productContents(searchName1);
        System.out.println("Товар '" + searchName1 + "' в корзине: " + result);
        String searchName2="Фотоаппарат";
        boolean result1= basket.productContents(searchName2);
        System.out.println("Товар '" + searchName1 + "' в корзине: " + result1);
        basket.clear();
        basket.basketContents();
        System.out.println("Общая стоимость: " + basket.totalCost() + " руб.");
        String searchName3 = "Ноутбук";
        boolean result2 = basket.productContents(searchName3);
        System.out.println("Товар '" + searchName3 + "' в корзине: " + result2);
    }

}
