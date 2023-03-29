package ru.mail.polis.homework.objects;


import java.util.Objects;

/**
 * Нужно найти символ, который встречается подряд в строке чаще всего, и указать количество повторений.
 * Если более одного символа с максимальным значением, то нужно вернуть тот символ,
 * который первый встречается в строчке
 * Если строка пустая или null, то вернуть null
 * Пример abbasbdlbdbfklsssbb -> (s, 3)
 */
public class RepeatingCharacters {

    public static Pair<Character, Integer> getMaxRepeatingCharacters(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        str = str.concat("_");
        int[] alphabet = new int[26];
        int[] firstInstance = new int[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = 0;
            firstInstance[i] = str.length();
        }
        int lastLetter = str.charAt(0) - 'a';
        int ammount = 1;
        for (int i = 1; i < str.length(); i++) {
            int currentLetter = str.charAt(i) - 'a';
            if (lastLetter == currentLetter) {
                ammount++;
            } else {
                if (ammount > alphabet[lastLetter]) {
                    alphabet[lastLetter] = ammount;
                    firstInstance[lastLetter] = i - ammount;
                }
                ammount = 1;
                lastLetter = currentLetter;
            }


        }
        int maxLetter = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] > alphabet[maxLetter] || (alphabet[i] == alphabet[maxLetter] && firstInstance[i] < firstInstance[maxLetter])) {
                maxLetter = i;
            }
        }
        char actualLetter = (char) ('a' + maxLetter);
        return new Pair<>(actualLetter, alphabet[maxLetter]);
    }

    public static class Pair<T, V> {
        private final T first;
        private final V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

    }
}
