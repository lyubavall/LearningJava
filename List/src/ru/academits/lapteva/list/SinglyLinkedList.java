package ru.academits.lapteva.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T getHeadData() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + (count - 1));
        }
    }

    private ListItem<T> findItem(int index) {
        ListItem<T> p = head;

        for (int i = 1; i <= index; ++i) {
            p = p.getNext();
        }

        return p;
    }

    public T getData(int index) {
        validateIndex(index);
        return findItem(index).getData();
    }

    public T setData(int index, T newData) {
        validateIndex(index);
        ListItem<T> p = findItem(index);
        T prevData = p.getData();
        p.setData(newData);
        return prevData;
    }

    public T delete(int index) {
        validateIndex(index);

        if (index == 0) {
            return deleteHead();
        }

        ListItem<T> prev = findItem(index - 1);
        T deletedData = prev.getNext().getData();
        prev.setNext(prev.getNext().getNext());
        count--;
        return deletedData;
    }

    public void addHead(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void insert(int index, T data) {
        if (index == 0) {
            addHead(data);
            return;
        }
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + count);
        }

        ListItem<T> prev = findItem(index - 1);
        ListItem<T> newItem = new ListItem<>(data, prev.getNext());
        prev.setNext(newItem);
        count++;
    }

    public boolean delete(T data) {
        if (Objects.equals(head.getData(), data)) {
            deleteHead();
            return true;
        } else {
            for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
                if (Objects.equals(p.getData(), data)) {
                    prev.setNext(p.getNext());
                    count--;
                    return true;
                }
            }
        }

        return false;
    }

    public T deleteHead() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }

        T data = head.getData();
        head = head.getNext();
        count--;
        return data;
    }

    public void reverse() {
        if (count < 2) {
            return;
        }

        ListItem<T> preItem;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = currentItem.getNext();
        currentItem.setNext(null);

        for (int i = 1; i < count - 1; i++) {
            preItem = currentItem;
            currentItem = nextItem;
            nextItem = nextItem.getNext();
            currentItem.setNext(preItem);
        }

        head = nextItem;
        head.setNext(currentItem);
    }

    public SinglyLinkedList<T> copy() {
        if (count == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> copy = new SinglyLinkedList<>();
        copy.addHead(head.getData());
        ListItem<T> copiedItem = copy.head;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> nextCopiedItem = new ListItem<>(p.getData());
            copiedItem.setNext(nextCopiedItem);
            copiedItem = nextCopiedItem;
        }

        copy.count = count;
        return copy;
    }

    @Override
    public String toString() {
        if (count <= 0) {
            return System.lineSeparator();
        }

        StringBuilder s = new StringBuilder();
        s.append(head.getData());

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            s.append(System.lineSeparator());
            s.append(p.getData());
        }

        return s.toString();
    }
}
