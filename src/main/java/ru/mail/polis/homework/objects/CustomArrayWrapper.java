package ru.mail.polis.homework.objects;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Вам придется реализовать Iterable класс CustomArrayWrapper вместе с методами которые
 * могут возващать итераторы только по четным/нечетным позициям в массиве. Пример с классического
 * итератора можете взять из лекции. Обратите внимание что подсчет четного или нечетного элемента
 * идет с человеческой точки зрения.
 * Пример:
 * дан массив [100, 0 ,100, 0, 100]
 * тогда все элементы со значением 100 имеют нечетную позицию, а элементы = 0 - четную.
 */
public class CustomArrayWrapper implements Iterable<Integer> {

    private final int[] array;          // массив
    private int position;               // следующая позиция куда будет вставлен элемент

    public CustomArrayWrapper(int size) {
        this.array = new int[size];
    }

    public void add(int value) {
        checkIndex(position);
        array[position] = value;
        position++;
    }

    public void edit(int index, int value) {
        checkIndex(index);
        array[index] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return array[index];
    }

    public int size() {
        return array.length;
    }

    /**
     * Реализовать метод:
     * Возврящает обычный итератор.
     *
     * @return default Iterator
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Integer index = 0;

            @Override
            public boolean hasNext() {
//                checkIndex(position);
                return index < position;
            }

            @Override
            public Integer next() {
                checkIndex(index);
                Integer ans = array[index];
                index++;
                return ans;
            }
        };
    }

    private Iterator<Integer> twoIterator(Integer start) {
        return new Iterator<>() {
            Integer index = start; // from 0 not 1

            final Integer startPosition = position;
            @Override
            public boolean hasNext() {
                checkModification(startPosition, position);
                return index < position;
            }

            @Override
            public Integer next() {
                checkModification(startPosition, position);
                checkIndex(index);
                Integer ans = array[index];
                index += 2;
                return ans;
            }
        };
    }

    /**
     * Реализовать метод:
     * Возвращает итератор который проходит только четные элементы.
     *
     * @return Iterator for EVEN elements
     */
    public Iterator<Integer> evenIterator() {
        return twoIterator(1);
    }

    /**
     * Реализовать метод:
     * Возвращает итератор который проходит нечетные элементы
     *
     * @return Iterator for ODD elements
     */
    public Iterator<Integer> oddIterator() {
        return twoIterator(0);
    }

    private void checkModification(int currentPosition, int previousPosition) {
        if(currentPosition != previousPosition) {
            throw new ConcurrentModificationException();
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

}
