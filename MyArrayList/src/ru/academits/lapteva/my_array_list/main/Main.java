package ru.academits.lapteva.my_array_list.main;

import ru.academits.lapteva.my_array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add(1, "11");

        System.out.println("Список: " + list1);
        System.out.println("Удалить по индексу 3 элемент " + list1.remove(3));
        System.out.println("Список: " + list1);
        System.out.println("Список содержит 6? " + list1.contains("6"));
        System.out.println("Размер списка больше 2? " + list1.ensureCapacity(2));

        list1.trimToSize();
        System.out.println("Массив: " + list1);

        String[] list1Array = list1.toArray(new String[5]);
        System.out.println(Arrays.toString(list1Array));

        System.out.println("Удалить элемент 11 " + list1.remove("11"));
        System.out.println("Список: " + list1);

        MyArrayList<String> list2 = new MyArrayList<>(10);
        list2.add("a");
        list2.add("b");

        list2.addAll(1, list1);
        System.out.println("Добавить список " + list2);

        System.out.println("Содержит ли этот список добавленный список? " + list2.containsAll(list1));
    }
}
