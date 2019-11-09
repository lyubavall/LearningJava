package ru.academits.lapteva.list.main;

import ru.academits.lapteva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addHead("5");
        list.addHead("4");
        list.addHead("3");
        list.addHead("2");
        list.addHead("1");

        System.out.println("Список: \n" + list);
        System.out.println("Размер списка: " + list.getCount());
        System.out.println("Голова списка: " + list.getHeadData());
        System.out.println("Получить значение по индексу 1: " + list.getData(1));
        System.out.println("Изменить значение " + list.changeData(0, "11") + " по индексу 0: \n" + list);
        System.out.println("Удаление элемента " + list.delete(1) + " по индексу 1");
        System.out.println("Список: \n" + list);
        System.out.println("Удаление головы " + list.deleteHead() + " списка: \n" + list);

        list.insert(2, "34");
        System.out.println("Вставка по индексу 2:\n" + list);
        System.out.println("Удалить элемент 34 " + list.delete("34"));
        System.out.println("Список: \n" + list);

        list.reverse();
        System.out.println("Развернуть список: \n" + list );

        SinglyLinkedList<String> copedList = list.copy();
        System.out.println("Скопированный список: \n" + copedList);
    }
}
