package ru.academits.lapteva.range.main;

import ru.academits.lapteva.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(5, 7);

        double length = range1.getLength();

        System.out.println("Длина диапазона " + length);

        double x = 5.2;

        System.out.println("x внутри отрезка? " + range1.isInside(x));

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Пересечений нет");
        } else {
            System.out.print("Пересечение отрезков: ");
            intersection.print();
            System.out.println();
        }

        Range[] union = range1.getUnion(range2);

        System.out.print("Объединение отрезков: ");

        for (Range range : union) {
            range.print();
        }

        System.out.println();

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Разность отрезков - пустой интервал");
        } else {
            System.out.print("Разность отрезков: ");

            for (Range range : difference) {
                range.print();
            }
        }
    }
}
