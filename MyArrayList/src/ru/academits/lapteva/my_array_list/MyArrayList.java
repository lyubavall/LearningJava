package ru.academits.lapteva.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modificationsCount = 0;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[6];
    }

    public MyArrayList(int capacity) {
        if (capacity == 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше нуля");
        }
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity(int expectedSize) {
        if (items.length < expectedSize) {
            items = Arrays.copyOf(items, expectedSize);
        }
    }

    public void trimToSize() {
        if (items.length != size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        int modificationsCountBeforeIterator = modificationsCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Нет следующего элемента");
            } else if (modificationsCount != modificationsCountBeforeIterator) {
                throw new ConcurrentModificationException("Список был изменен во время обхода");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size);
        } else {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(items, 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }

            return a;
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(T t) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = t;
        ++size;
        ++modificationsCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            --size;
            ++modificationsCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int matchesCount = 0;

        for (Object e : c) {
            if (contains(e)) {
                ++matchesCount;
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(size, c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        validateIndexForAdding(index);
        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(size + c.size());
        if (size > index) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        for (T e : c) {
            items[index] = e;
            ++index;
        }

        ++modificationsCount;
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0 || size == 0) {
            return false;
        }

        boolean isRemove = false;

        for (Object e : c) {
            if (contains(e)) {
                remove(e);
                isRemove = true;
            }
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0 || size == 0) {
            return false;
        }

        boolean isRemove = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
                isRemove = true;
            }
        }

        return isRemove;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        ++modificationsCount;
        size = 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + (size - 1));
        }
    }

    private void validateIndexForAdding(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + size);
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        validateIndex(index);
        T oldElement = items[index];
        items[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        validateIndexForAdding(index);
        ensureCapacity(size + 1);
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        ++size;
        ++modificationsCount;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T oldElement = items[index];
        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        --size;
        ++modificationsCount;
        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size(); i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[").append(items[0]);

        for (int i = 1; i < size; ++i) {
            s.append(", ").append(items[i]);
        }

        return s.append("]").toString();
    }
}
