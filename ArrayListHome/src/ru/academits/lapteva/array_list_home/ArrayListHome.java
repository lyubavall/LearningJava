package ru.academits.lapteva.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/academits/lapteva/array_list_home/file.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
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

        for (Integer integer : list3) {
            if (!newList3.contains(integer)) {
                newList3.add(integer);
            }
        }

        System.out.println("без повторений: " + newList3);
    }
}
