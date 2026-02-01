package org.skypro.skyshop.search;
import java.util.Arrays;
public class SearchEngine  {
    private final Searchable[] items;
    private int count;

    public SearchEngine(int capacity) {

        this.items = new Searchable[capacity];
        this.count = 0;
    }

    public void add(Searchable item) {


        if (count < items.length) {
            items[count] = item;
            count++;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String lowerCaseQuery = query.toLowerCase().trim();

        int foundCount = 0;


        for (int i = 0; i < count && foundCount < 5; i++) {
            Searchable item = items[i];

            if (item != null) {
                String searchTerm = item.getSearchTerm();


                if (searchTerm != null) {
                    String lowerCaseSearchTerm = searchTerm.toLowerCase();


                    if (lowerCaseSearchTerm.contains(lowerCaseQuery)) {
                        results[foundCount] = item;
                        foundCount++;
                    }
                }
            }
        }

        System.out.println("Всего найдено результатов: " + foundCount);


        return Arrays.stream(results)
                .filter(item -> item != null)
                .toArray(Searchable[]::new);
    }

    public int getCount() {
        return count;
    }

    public int getCapacity() {
        return items.length;
    }
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
                    // Реализация алгоритма из задания
                    int count = 0;
                    int index = 0;
                    int substringIndex = searchTerm.indexOf(search, index);

                    while (substringIndex != -1) {
                        count++;
                        index = substringIndex + search.length();
                        substringIndex = searchTerm.indexOf(search, index);
                    }

                    if (count > maxCount) {
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









