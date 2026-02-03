package org.skypro.skyshop.search;
import java.util.*;
import java.util.stream.Collectors;

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
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Всего найдено результатов: 0");
            return new TreeSet<>(getComparator());
        }

        String lowerCaseQuery = query.toLowerCase().trim();

        Set<Searchable> results = items.stream()
                .filter(item -> item != null && item.getSearchTerm() != null)
                .filter(item -> item.getSearchTerm().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toCollection(() -> new TreeSet<>(getComparator())));

        System.out.println("Всего найдено результатов: " + results.size());
        return results;
    }

    private Comparator<Searchable> getComparator() {
        return (s1, s2) -> {
            // Сравниваем по длине имени (обратный порядок - от большего к меньшему)
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            // При равной длине - по натуральному порядку имени
            return s1.getName().compareTo(s2.getName());
        };
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










