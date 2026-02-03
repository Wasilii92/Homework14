package org.skypro.skyshop.search;
import java.util.*;

public class SearchEngine  {
    private final Set<Searchable> items;
    private int count;

    public SearchEngine(int capacity) {
        this.items = new HashSet<>();
        this.count = 0;
    }

    public void add(Searchable item) {
        items.add(item);
        count++;
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> comparator = (s1, s2) -> {
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            return s1.getName().compareTo(s2.getName());
        };

        Set<Searchable> results = new TreeSet<>(comparator);

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










