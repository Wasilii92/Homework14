package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        SimpleProduct laptop = new SimpleProduct("Ноутбук", 75000);
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 35000, 15);
        FixPriceProduct headphones = new FixPriceProduct("Наушники");
        SimpleProduct tablet = new SimpleProduct("Планшет", 25000);
        SimpleProduct charger = new SimpleProduct("Зарядное устройство", 1500);
        SimpleProduct mouse = new SimpleProduct("Мышь", 1200);

        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(charger);
        basket.addProduct(mouse);
        basket.basketContents();

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

        searchEngine.add(new SimpleProduct("Ноутбук", 80000));
        searchEngine.add(new Article("Лучшие наушники 2024", "Другое описание"));
        System.out.println("В SearchEngine добавлено: " + searchEngine.getCount() + " элементов");

        System.out.println("\n1. Поиск по запросу 'Ноутбук':");
        Set<Searchable> results = searchEngine.search("Ноутбук");
        System.out.println("Результаты: ");
        for (Searchable result : results)  {
            System.out.println("  - " + result.getSearchTerm());
        }

        System.out.println("\n2. Поиск по запросу 'смартфон':");
        Set<Searchable> results2 = searchEngine.search("смартфон");
        System.out.println("Результаты: ");
        for (Searchable result : results2) {
            System.out.println("  - " + result.getSearchTerm());
        }

        System.out.println("\n3. Поиск по запросу '2024':");
        Set<Searchable> results3 = searchEngine.search("2024");
        System.out.println("Результаты: ");
        for (Searchable result : results3) {
            System.out.println("  - " + result.getSearchTerm());
        }

        System.out.println("\n4. Поиск по запросу 'выбрать':");
        Set<Searchable> results4 = searchEngine.search("выбрать");
        System.out.println("Результаты: ");
        for (Searchable result : results4) {
            System.out.println("  - " + result.getSearchTerm());
        }

        System.out.println("\n5. Поиск по запросу 'xyz' (нет результатов):");
        Set<Searchable> results5 = searchEngine.search("xyz");
        System.out.println("Результаты: " + (results5.isEmpty() ? "Список пуст" : "Найдено " + results5.size() + " элементов"));


        SearchEngine bestMatchEngine = new SearchEngine(20);

        bestMatchEngine.add(new Article("Тестовый текст hello hello hello", "Описание 1"));
        bestMatchEngine.add(new Article("Текст с hello hello", "Описание 2"));
        bestMatchEngine.add(new Article("Просто hello", "Описание 3"));
        bestMatchEngine.add(new Article("Без искомого слова", "Описание 4"));

        System.out.println("\nТест 1: Поиск 'hello' в текстах");
        try {
            Searchable bestMatch = bestMatchEngine.findBestMatch("hello");
            System.out.println("Лучший результат: " + bestMatch.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        System.out.println("\nТест 2: Поиск несуществующего слова");
        try {
            Searchable bestMatch = bestMatchEngine.findBestMatch("несуществующее");
            System.out.println("Лучший результат: " + bestMatch.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        ProductBasket demoBasket = new ProductBasket();

        SimpleProduct phone1 = new SimpleProduct("Смартфон", 35000);
        SimpleProduct phone2 = new SimpleProduct("Смартфон", 35000); // дубликат
        SimpleProduct laptop1 = new SimpleProduct("Ноутбук", 75000);
        SimpleProduct headphones1 = new SimpleProduct("Наушники", 12000);
        SimpleProduct tablet1 = new SimpleProduct("Планшет", 25000);
        SimpleProduct charger1 = new SimpleProduct("Зарядное устройство", 1500);

        demoBasket.addProduct(phone1);
        demoBasket.addProduct(phone2);
        demoBasket.addProduct(laptop1);
        demoBasket.addProduct(headphones1);
        demoBasket.addProduct(tablet1);
        demoBasket.addProduct(charger1);

        System.out.println("1. Исходное содержимое корзины:");
        demoBasket.basketContents();

        System.out.println("\n2. Удаляем продукт 'Смартфон':");
        List<Product> removedProducts = demoBasket.removeProductByName("Смартфон");
        System.out.println("Удаленные продукты:");
        for (Product product : removedProducts) {
            System.out.println("  - " + product.getName() + " (" + product.getPrice() + " руб.)");
        }

        System.out.println("\n3. Содержимое корзины после удаления:");
        demoBasket.basketContents();

        System.out.println("\n4. Удаляем несуществующий продукт 'Телевизор':");
        List<Product> notFoundProducts = demoBasket.removeProductByName("Телевизор");
        if (notFoundProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты: " + notFoundProducts.size());
        }

        System.out.println("\n5. Финальное содержимое корзины:");
        demoBasket.basketContents();
    }
}

