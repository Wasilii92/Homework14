package org.skypro.skyshop.search;
    public class BestResultNotFound extends Exception {
        public BestResultNotFound(String searchQuery) {
            super("Не удалось найти наиболее подходящий объект для поискового запроса: \"" + searchQuery + "\"");
        }
    }

