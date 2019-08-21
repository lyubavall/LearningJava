package ru.academits.lapteva.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/academits/lapteva/array_list_home/file.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        }

        System.out.println(list1);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 7, 4, 2, 2, 3, 4));
        System.out.println("Список чисел: " + list);

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }

        System.out.println("только нечетные: " + list);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 7, 4, 2, 2, 3, 4));
        ArrayList<Integer> newList3 = new ArrayList<>();
        newList3.add(list3.get(0));

        for (int i = 1; i < list3.size(); ++i) {
            if (!newList3.contains(list3.get(i))) {
                newList3.add(list3.get(i));
            }
        }

        System.out.println("без повторений: " + newList3);
    }
}
