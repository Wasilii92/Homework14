package org.skypro.skyshop.search;
import java.util.*;

public class SearchEngine  {
    private final List<Searchable> items;
    private int count;

    public SearchEngine(int capacity) {
        this.items = new LinkedList<>();
        this.count = 0;
    }

    public void add(Searchable item) {
        items.add(item);
        count++;
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> results = new TreeMap<>();

        if (query == null || query.trim().isEmpty()) {
            System.out.println("Всего найдено результатов: 0");
            return results;
        }

        String lowerCaseQuery = query.toLowerCase().trim();

        for (Searchable item : items) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null) {
                    String lowerCaseSearchTerm = searchTerm.toLowerCase();
                    if (lowerCaseSearchTerm.contains(lowerCaseQuery)) {
                        // Используем имя как ключ, TreeMap автоматически сортирует по ключу
                        results.put(searchTerm, item);
                    }
                }
            }
        }

        System.out.println("Всего найдено результатов: " + results.size());
        return results;
    }
    public int getCount() {
        return count;
    }


    public int getCapacity() {
        return items.size();
    }

        Searchable bestMatch = null;
        int maxCount = -1;

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable item : items) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null) {
                    int count = 0;
                    int index = 0;
                    int substringIndex = searchTerm.indexOf(search, index);

                    while (substringIndex != -1) {
                        count++;
                        index = substringIndex + search.length();
                        substringIndex = searchTerm.indexOf(search, index);
                    }

                    if (count > 0 && count > maxCount) {
                        maxCount = count;
                        bestMatch = item;
                    }
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }
}










