package ru.mail.polis.homework.objects;

public class MaxTask {

    /**
     * Вам дан массив и количество элементов в возвращаемом массиве
     * Вернуть нужно массив из count максимальных элементов array, упорядоченный по убыванию.
     * Если массив null или его длина меньше count, то вернуть null
     * Например ({1, 3, 10, 11, 22, 0}, 2) -> {22, 11}
     * ({1, 3, 22, 11, 22, 0}, 3) -> {22, 22, 11}
     * НЕЛЬЗЯ СОРТИРОВАТЬ массив array и его копии
     *
     */
    public static int[] getMaxArray(int[] array, int count) {
        if(array == null || count > array.length) {
            return null;
        }
        if(count == 0) {
            return new int[]{};
        }
        int[] ans = new int[count];
        for(int i = 0; i < count; i++) {
            ans[i] = Integer.MIN_VALUE;
        }

        for (int k : array) {
            if (k > ans[count - 1]) {
                int j = count - 1;
                while (j - 1 >= 0 && ans[j - 1] < k) {
                    ans[j] = ans[j - 1];
                    j--;
                }
                ans[j] = k;
            }
        }
        return ans;
    }

}
