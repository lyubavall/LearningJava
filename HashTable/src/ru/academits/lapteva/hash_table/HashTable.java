package ru.academits.lapteva.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;
    private int elementsCount;
    private int modificationsCount;

    public HashTable() {
        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[11];
    }

    public HashTable(int capacity) {
        if (capacity == 0) {
            throw new IllegalArgumentException("Вместимость Хэш-таблицы не должна быть нулевая");
        }
        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[capacity];
    }

    private int getIndex(T element) {
        return Math.abs(element.hashCode() % hashTable.length);
    }

    @Override
    public int size() {
        return hashTable.length;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex((T) o);
        return (index < hashTable.length) && (hashTable[index] != null) && (hashTable[index].contains(o));
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int subCurrentIndex = -1;
        private int iteratedElementsCount = 0;
        int modificationsCountBeforeIterator = modificationsCount;
        boolean isNewLine = true;

        @Override
        public boolean hasNext() {
            return iteratedElementsCount < elementsCount;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Нет следующего элемента");
            } else if (modificationsCount != modificationsCountBeforeIterator) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена во время обхода");
            }

            if (isNewLine) {
                ++currentIndex;
                while (hashTable[currentIndex] == null) {
                    ++currentIndex;
                }

                subCurrentIndex = -1;
            }

            ++subCurrentIndex;
            isNewLine = subCurrentIndex >= hashTable[currentIndex].size() - 1;
            ++iteratedElementsCount;
            return hashTable[currentIndex].get(subCurrentIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementsCount];
        int i = 0;
        for(T element: this){
            array[i] = element;
            ++i;
        }
        
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < elementsCount) {
            //noinspection unchecked
            return (T1[]) toArray();
        } else {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(toArray(), 0, a, 0, elementsCount);
            if (a.length > elementsCount) {
                a[elementsCount] = null;
            }

            return a;
        }
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        hashTable[index].add(t);
        ++elementsCount;
        ++modificationsCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex((T) o);
        if ((index < hashTable.length) && (hashTable[index] != null) && (hashTable[index].remove(o))) {
            --elementsCount;
            ++modificationsCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int matchesCount = 0;
        for (Object element: c) {
            if(contains(element)){
                ++matchesCount;
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for(T element: c){
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        for(Object element: c){
            remove(element);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTable, null);
        ++modificationsCount;
        elementsCount = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hashTable.length; ++i) {
            if (hashTable[i] != null) {
                s.append(i).append(": ").append(hashTable[i].toString());
            } else {
                s.append(i).append(": null");
            }

            s.append(System.lineSeparator());
        }

        return s.toString();
    }
}
