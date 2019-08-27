package ru.academits.lapteva.vector.main;

import ru.academits.lapteva.vector.Vector;

import static ru.academits.lapteva.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(5, new double[]{1, 2, 3});
        Vector v2 = new Vector(new double[]{1, 0, 0, 0});

        System.out.println("Первый вектор: " + v1);
        System.out.println("Второй вектор: " + v2);
        System.out.println("Нестатические методы:");
        System.out.println("Размерность вектора: " + v2.getSize());
        System.out.println("Сумма векторов: " + v1.getVectorsSum(v2));
        System.out.println("Разница векторов: " + v1.getVectorsDifference(v2));
        System.out.println("Скалярное умножение вектора: " + v1.multiplyVectorScalarly(2));
        System.out.println("Разворот вектора: " + v1.reflectVector());
        System.out.println("Длина вектора: " + v2.getVectorLength());
        v2.setComponent(0, 100);
        System.out.println("Получить установленную комоненту: " + v2.getComponent(0));
        System.out.println();

        Vector v3 = new Vector(v1);
        Vector v4 = new Vector(4);

        System.out.println("Первый вектор: " + v3);
        System.out.println("Второй вектор: " + v4);
        System.out.println("Статические методы:");
        System.out.println("Сумма векторов: " + getVectorsSum(v3, v4));
        System.out.println("Разница векторов: " + getVectorsDifference(v3, v4));
        System.out.println("Скалярное умножение векторов: " + getScalarMultiplication(v3, v4));
    }
}
