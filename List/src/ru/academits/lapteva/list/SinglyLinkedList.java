package ru.academits.lapteva.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getHeadData() {
        return head.getData();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс должен быть в пределах от 0 до " + (count - 1));
        }
    }

    public T getData(int index) {
        validateIndex(index);

        ListItem<T> p = head;

        if (index > 0) {
            for (int i = 1; i <= index; ++i) {
                p = p.getNext();
            }
        }

        return p.getData();
    }

    public T changeData(int index, T newData) {
        validateIndex(index);

        ListItem<T> p = head;
        T preData = head.getData();

        if (index > 0) {
            for (int i = 1; i <= index; ++i) {
                p = p.getNext();
            }

            preData = p.getData();
        }

        p.setData(newData);
        return preData;
    }

    public T delete(int index) {
        if (index == 0) {
            deleteHead();
        }

        validateIndex(index);

        ListItem<T> p = head;

        for (int i = 1; i < index; ++i) {
            p = p.getNext();
        }

        T deletedData = p.getNext().getData();
        p.setNext(p.getNext().getNext());

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
        } else {
            if (index > count || index < 0) {
                throw new IllegalArgumentException("Индекс должен быть в пределах от 0 до " + count);
            }

            ListItem<T> p = head;

            for (int i = 1; i < index; ++i) {
                p = p.getNext();
            }

            ListItem<T> newItem = new ListItem<>(data, (index == count ? null : p.getNext()));
            p.setNext(newItem);

            count++;
        }
    }

    public boolean delete(T data) {
        boolean isDeleted = false;
        int index = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {

            if (p.getData().equals(data)) {
                delete(index);
                isDeleted = true;
            }

            index++;
        }

        return isDeleted;
    }

    public T deleteHead() {
        T data = head.getData();
        head = head.getNext();

        count--;

        return data;
    }

    public void reverse() {
        if (count < 2) {
            return;
        }
        if (count == 2) {
            ListItem<T> second = head.getNext();

            head.setNext(null);
            second.setNext(head);
            head = second;
        } else {
            ListItem<T> preItem = head;
            ListItem<T> currentItem = preItem.getNext();
            ListItem<T> nextItem = currentItem.getNext();

            preItem.setNext(null);
            currentItem.setNext(preItem);

            if (count > 2) {
                for (int i = 2; i < count - 1; i++) {
                    preItem = currentItem;
                    currentItem = nextItem;
                    nextItem = nextItem.getNext();
                    currentItem.setNext(preItem);
                }
            }

            head = nextItem;
            head.setNext(currentItem);
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copedList = new SinglyLinkedList<>();
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            copedList.insert(i, p.getData());
            i++;
        }

        return copedList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(head.getData());

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            s.append(System.getProperty("line.separator")).append(p.getData());
        }

        return s.toString();
    }
}
