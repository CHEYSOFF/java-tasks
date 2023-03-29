package ru.mail.polis.homework.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 15 тугриков
 * Реализовать все методы односвязного списка.
 */
public class CustomLinkedList implements Iterable<Integer> {

    private Node head;
    private Node tail;
    private Integer size_ = 0;

    private Node getNode(int index) {
        Node cur = tail;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 1 тугрик
     * Возвращает количество элементов в списке
     *
     * @return size
     */
    public int size() {
        return size_;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Добавляет элемент в односвязный список.
     *
     * @param value - data for create Node.
     */
    public void add(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            tail = newNode;
        } else {
            head.next = newNode;
        }
        head = newNode;
        head.next = null;
        size_++;
    }

    /**
     * 2 тугрика
     * Метод должен вернуть число на соответствующем индексе.
     *
     * @param index
     */
    public int get(int index) {
        if(index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
        Node cur = getNode(index);
        return cur.value;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Добавляет элемент в односвязный список на заданную позицию.
     * Если был передан невалидный index - надо выкинуть исключение IndexOutOfBoundsException.
     * throw new IndexOutOfBoundsException(i);
     *
     * @param i     - index
     * @param value - data for create Node.
     */
    public void add(int i, int value) {
        if(i == 0) {
            Node newNode = new Node(value);
            newNode.next = tail;
            tail = newNode;
            size_++;
            return;
        }

        if(i - 1 >= size() || i < 0) {
            throw new IndexOutOfBoundsException(i - 1);
        }
        Node cur = getNode(i - 1);
        Node newNode = new Node(value);
        newNode.next = cur.next;
        cur.next = newNode;
        size_++;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Удаляет элемент в указанной позиции, при это связывая его соседние элементы друг с другом.
     * Если был передан невалидный index - надо выкинуть исключение IndexOutOfBoundsException.
     * throw new IndexOutOfBoundsException(i);
     *
     * @param index - position what element need remove.
     */
    public void removeElement(int index) {
        if(index >= size() || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        if(index == 0) {
            tail = tail.next;
            if(size() == 1) {
                head = null;
            }
        }
        else{
            Node prev = getNode(index - 1);
            prev.next = prev.next.next;
            if(index == size() - 1) {
                head = prev;
            }

        }
        size_--;
    }

    /**
     * 2 тугрика
     * Реализовать метод:
     * Переворачивает все элементы списка.
     * Пример:
     * Исходная последовательность списка "1 -> 2 -> 3 -> 4 -> null"
     * После исполнения метода последовательность должна быть такой "4 -> 3 -> 2 -> 1 -> null"
     */
    public void revertList() {
        if(tail == null) {
            return;
        }
        Node prev = null;
        Node cur = tail;
        while(cur != null) {
            Node next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        Node tmp = tail;
        tail = head;
        head = tmp;
    }

    /**
     * 1 тугрик
     * Метод выводит всю последовательность хранящуюся в списке начиная с head.
     * Формат вывода:
     * - значение каждой Node должно разделяться " -> "
     * - последовательность всегда заканчивается на null
     * - если в списке нет элементов - верните строку "null"
     *
     * @return - String with description all list
     */
    @Override
    public String toString() {
        if(size() == 0) {
            return "null";
        }
        StringBuilder ans = new StringBuilder(Integer.toString(tail.value) + " -> ");
        Node cur = tail.next;
        while(cur != null) {
            ans.append(cur.value).append(" -> ");
            cur = cur.next;
        }
        ans.append("null");
        return ans.toString();
    }

    /**
     * 3 тугрика
     * Возвращает итератор, который умеет только итерироваться. БЕЗ удаления!
     *
     * @return iterator
     */
    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            Node node = tail;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Integer next() {
                if(hasNext()) {
                    Integer value = node.value;
                    node = node.next;
                    return value;
                }
                else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
