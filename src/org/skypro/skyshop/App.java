package org.skypro.skyshop;

import org.skypro.skyshop.basket.productBasket;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.simpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

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
        Article article1 = new Article(
                "Обзор ноутбуков 2024 года",
                "Новые ноутбуки 2024 года стали мощнее и энергоэффективнее."
        );

        Article article2 = new Article(
                "Как выбрать смартфон",
                "При выборе смартфона обратите внимание на процессор, камеру и время работы от аккумулятора."
        );

        Article article3 = new Article(
                "Лучшие наушники 2024",
                "Топ-5 наушников по версии экспертов. Sony, Bose, Apple и другие."
        );

        SearchEngine searchEngine = new SearchEngine(15);
        searchEngine.add(laptop);
        searchEngine.add(phone);
        searchEngine.add(headphones);
        searchEngine.add(tablet);
        searchEngine.add(charger);
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        System.out.println("В SearchEngine добавлено: " + searchEngine.getCount() + " элементов");


        System.out.println("\n1. Поиск по запросу 'Ноутбук':");
        Searchable[] results1 = searchEngine.search("Ноутбук");
        System.out.println("Результаты: " + Arrays.toString(results1));


        System.out.println("\n2. Поиск по запросу 'смартфон':");
        Searchable[] results2 = searchEngine.search("смартфон");
        System.out.println("Результаты: " + Arrays.toString(results2));

        System.out.println(" \n3. Поиск по запросу '2024':");
        Searchable[] results3 = searchEngine.search("2024");
        System.out.println("Результаты: " + Arrays.toString(results3));


        System.out.println("\n4. Поиск по запросу 'выбрать':");
        Searchable[] results4 = searchEngine.search("выбрать");
        System.out.println("Результаты: " + Arrays.toString(results4));


        System.out.println("\n5. Поиск по запросу 'xyz' (нет результатов):");
        Searchable[] results5 = searchEngine.search("xyz");
        System.out.println("Результаты: " + Arrays.toString(results5));
    }

}
