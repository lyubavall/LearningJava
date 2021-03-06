package ru.academits.lapteva.hash_table.main;

import ru.academits.lapteva.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> table1 = new HashTable<>();
        String s = "a";
        table1.add(s);
        table1.add("b");
        table1.add("с");
        table1.add("a");
        table1.add(null);
        table1.add("w");

        System.out.println("Хэш-таблица: " + System.lineSeparator() + table1);

        ArrayList<String> list6 = new ArrayList<>();
        list6.add(null);
        System.out.println("Список содержит null? " + table1.containsAll(list6));

        //noinspection ArraysAsListWithZeroOrOneArgument
        ArrayList<String> st = new ArrayList<>(Arrays.asList("a"));
        table1.retainAll(st);

        System.out.println("Оставить только а:" + System.lineSeparator() + table1);
        System.out.println("Удалить элемент w: " + table1.remove("w"));
        System.out.println("Элементы хэш-таблицы:");

        for (String e : table1) {
            System.out.println(e);
        }

        HashTable<String> table2 = new HashTable<>(5);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "3", "13", null));
        table2.addAll(list);

        System.out.println("Добавить список в хэш-таблицу: " + System.lineSeparator() + table2);

        Object[] array = table2.toArray();
        System.out.println("В массив: " + Arrays.toString(array));

        //noinspection SuspiciousMethodCalls
        table2.removeAll(Arrays.asList(array));
        System.out.println("Удалить массив из хэш-таблицы: " + System.lineSeparator() + table2);
    }
}
