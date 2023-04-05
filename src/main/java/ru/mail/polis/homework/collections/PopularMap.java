package ru.mail.polis.homework.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Написать структуру данных, реализующую интерфейс мапы + набор дополнительных методов.
 * 4 дополнительных метода должны возвращать самый популярный ключ и его популярность. (аналогично для самого популярного значения)
 * Популярность - это количество раз, который этот ключ/значение учавствовал/ло в других методах мапы, такие как
 * containsKey, get, put, remove (в качестве параметра и возвращаемого значения).
 * Считаем, что null я вам не передаю ни в качестве ключа, ни в качестве значения
 * <p>
 * Так же надо сделать итератор (подробности ниже).
 * <p>
 * Важный момент, вам не надо реализовывать мапу, вы должны использовать композицию.
 * Вы можете использовать любые коллекции, которые есть в java.
 * <p>
 * Помните, что по мапе тоже можно итерироваться
 * <p>
 * for (Map.Entry<K, V> entry : map.entrySet()) {
 * entry.getKey();
 * entry.getValue();
 * }
 * <p>
 * Всего 10 тугриков (3 тугрика за общие методы, 2 тугрика за итератор, 5 тугриков за логику популярности)
 *
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class PopularMap<K, V> implements Map<K, V> {

    private final Map<K, V> map;
    private final Map<V, Integer> popularityValue;
    private final Map<K, Integer> popularityKey;


    public PopularMap() {
        this.map = new HashMap<>();
        popularityValue = new HashMap<>();
        popularityKey = new HashMap<>();
    }

    public PopularMap(Map<K, V> map) {
        this.map = map;
        popularityValue = new HashMap<>();
        popularityKey = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        increaseKey(key);
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        increaseValue(value);
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        increaseKey(key);
        V value = map.get(key);
        increaseValue(value);
        return value;
    }

    @Override
    public V put(K key, V value) {
        increaseKey(key);
        increaseValue(value);
        if(map.get(key) == value) {
            increaseValue(value);
        }
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        increaseKey(key);
        if(map.containsKey(key)) {
            increaseValue(map.get(key));
        }
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * Возвращает самый популярный, на данный момент, ключ
     */
    public K getPopularKey() {
        return popularityKey.entrySet()
                .stream()
                .max(Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Возвращает количество использование ключа
     */
    public int getKeyPopularity(K key) {
        if(!popularityKey.containsKey(key)) {
            return 0;
        }
        return popularityKey.get(key);
    }

    /**
     * Возвращает самое популярное, на данный момент, значение. Надо учесть что значени может быть более одного
     */
    public V getPopularValue() {
        return popularityValue.entrySet()
                .stream()
                .max(Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Возвращает количество использований значений в методах: containsValue, get, put (учитывается 2 раза, если
     * старое значение и новое - одно и тоже), remove (считаем по старому значению).
     */
    public int getValuePopularity(V value) {
        if(!popularityValue.containsKey(value)) {
            return 0;
        }
        return popularityValue.get(value);
    }

    /**
     * Вернуть итератор, который итерируется по значениям (от самых НЕ популярных, к самым популярным)
     * 2 тугрика
     */
    public Iterator<V> popularIterator() {
        return new Iterator<V>() {
            final Iterator<Entry<V, Integer>> it = popularityValue.entrySet().iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public V next() {
                return it.next().getKey();
            }
        };
    }

    private void increaseSmth(Object obj, Map<Object, Integer> map) {
        Integer newValue = 0;
        if(map.containsKey(obj)) {
            newValue = map.get(obj);
        }
        map.put(obj, newValue + 1);
    }

    private void increaseKey(Object key) {
        increaseSmth(key, (Map<Object, Integer>) popularityKey);
    }

    private void increaseValue(Object value) {
        increaseSmth(value, (Map<Object, Integer>) popularityValue);
    }

}
