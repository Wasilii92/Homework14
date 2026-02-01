package org.skypro.skyshop.search;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String lowerCaseQuery = query.toLowerCase().trim();

        for (Searchable item : items) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null) {
                    String lowerCaseSearchTerm = searchTerm.toLowerCase();
                    if (lowerCaseSearchTerm.contains(lowerCaseQuery)) {
                        results.add(item);
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










