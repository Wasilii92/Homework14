package org.skypro.skyshop;

import org.skypro.skyshop.basket.productBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.simpleProduct;

public class App {
    public static void main(String[] args) {
        simpleProduct laptop=new simpleProduct("Ноутбук", 75000);
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 35000, 15);
        FixPriceProduct headphones = new FixPriceProduct("Наушники");
        simpleProduct tablet = new simpleProduct("Планшет", 25000);
        simpleProduct charger = new simpleProduct("Зарядное устройство", 1500);
        simpleProduct mouse = new simpleProduct("Мышь", 1200);
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
        System.out.println("Товар '" + searchName1 + " в корзине: " + result);
        String searchName2="Телевизор";
        boolean result1= basket.productContents(searchName2);
        System.out.println("Товар '" + searchName2 + "' в корзине: " + result1);
        basket.clear();
        basket.basketContents();
        System.out.println("Общая стоимость: " + basket.totalCost() + " руб.");
        String searchName3 = "Ноутбук";
        boolean result2 = basket.productContents(searchName3);
        System.out.println("Товар '" + searchName3 + "' в корзине: " + result2);
    }

}
