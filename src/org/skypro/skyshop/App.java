package org.skypro.skyshop;

import org.skypro.skyshop.basket.productBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        try {
            SimpleProduct badProduct1 = new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            SimpleProduct badProduct2 = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            SimpleProduct badProduct3 = new SimpleProduct(null, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n4. Создание продукта с ценой 0:");
        try {
            SimpleProduct badProduct4 = new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n5. Создание продукта с отрицательной ценой:");
        try {
            SimpleProduct badProduct5 = new SimpleProduct("Товар", -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n6. Создание DiscountedProduct с отрицательной скидкой:");
        try {
            DiscountedProduct badProduct6 = new DiscountedProduct("Товар со скидкой", 100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n7. Создание DiscountedProduct со скидкой > 100:");
        try {
            DiscountedProduct badProduct7 = new DiscountedProduct("Товар со скидкой", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n8. Создание DiscountedProduct с базовой ценой 0:");
        try {
            DiscountedProduct badProduct8 = new DiscountedProduct("Товар со скидкой", 0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        SimpleProduct laptop = new SimpleProduct("Ноутбук", 75000);
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 35000, 15);
        FixPriceProduct headphones = new FixPriceProduct("Наушники");
        SimpleProduct tablet = new SimpleProduct("Планшет", 25000);
        SimpleProduct charger = new SimpleProduct("Зарядное устройство", 1500);
        SimpleProduct mouse = new SimpleProduct("Мышь", 1200);

        productBasket basket = new productBasket();
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(charger);
        basket.addProduct(mouse);
        basket.basketContents();

        String searchName1 = "Смартфон";
        boolean result = basket.productContents(searchName1);
        System.out.println("Товар '" + searchName1 + " в корзине: " + result);

        String searchName2 = "Телевизор";
        boolean result1 = basket.productContents(searchName2);
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

        // Создаем SearchEngine с массивом
        SearchEngine searchEngine = new SearchEngine(5);

        // Тест 1: Поиск существующего товара
        System.out.println("Тест 1: Поиск запроса 'ноутбук'");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("ноутбук");
            System.out.println("Найден лучший результат: " + bestMatch.getSearchTerm());
        } catch (org.skypro.skyshop.search.BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        // Тест 2: Поиск несуществующего товара
        System.out.println("\nТест 2: Поиск запроса 'телевизор'");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("телевизор");
            System.out.println("Найден лучший результат: " + bestMatch.getSearchTerm());
        } catch (org.skypro.skyshop.search.BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        // Тест 3: Поиск в пустом массиве
        System.out.println("\nТест 3: Поиск в пустом массиве");
        SearchEngine emptyEngine = new SearchEngine(15);
        try {
            Searchable bestMatch = emptyEngine.findBestMatch("ноутбук");
            System.out.println("Найден лучший результат: " + bestMatch.getSearchTerm());
        } catch (org.skypro.skyshop.search.BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        // Тест 4: Поиск слова, которое встречается несколько раз
        System.out.println("\nТест 4: Поиск запроса '2024'");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("2024");
            System.out.println("Найден лучший результат: " + bestMatch.getSearchTerm());
        } catch (org.skypro.skyshop.search.BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        // Тест 5: Поиск с пустым запросом
        System.out.println("\nТест 5: Поиск пустого запроса");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("");
            System.out.println("Найден лучший результат: " + bestMatch.getSearchTerm());
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение IllegalArgumentException: " + e.getMessage());
        } catch (org.skypro.skyshop.search.BestResultNotFound e) {
            System.out.println("Исключение BestResultNotFound: " + e.getMessage());
        }

        // создаем SearchEngine с размером
        SearchEngine SearchEngine = new SearchEngine(15);
        SearchEngine.add(laptop);
        SearchEngine.add(phone);
        SearchEngine.add(headphones);
        SearchEngine.add(tablet);
        SearchEngine.add(charger);
        SearchEngine.add(article1);
        SearchEngine.add(article2);
        SearchEngine.add(article3);

        System.out.println("В SearchEngine добавлено: " + SearchEngine.getCount() + " элементов");

        System.out.println("\n1. Поиск по запросу 'Ноутбук':");
        Searchable[] results1 = SearchEngine.search("Ноутбук");
        System.out.println("Результаты: " + Arrays.toString(results1));

        System.out.println("\n2. Поиск по запросу 'смартфон':");
        Searchable[] results2 = SearchEngine.search("смартфон");
        System.out.println("Результаты: " + Arrays.toString(results2));

        System.out.println(" \n3. Поиск по запросу '2024':");
        Searchable[] results3 = SearchEngine.search("2024");
        System.out.println("Результаты: " + Arrays.toString(results3));

        System.out.println("\n4. Поиск по запросу 'выбрать':");
        Searchable[] results4 = SearchEngine.search("выбрать");
        System.out.println("Результаты: " + Arrays.toString(results4));

        System.out.println("\n5. Поиск по запросу 'xyz' (нет результатов):");
        Searchable[] results5 = SearchEngine.search("xyz");
        System.out.println("Результаты: " + Arrays.toString(results5));
    }
}

