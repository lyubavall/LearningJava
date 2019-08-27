package ru.academits.lapteva.shapes.main;

import ru.academits.lapteva.shapes.Shape;
import ru.academits.lapteva.shapes.Circle;
import ru.academits.lapteva.shapes.comparators.AreaComparator;
import ru.academits.lapteva.shapes.comparators.PerimeterComparator;
import ru.academits.lapteva.shapes.Rectangle;
import ru.academits.lapteva.shapes.Square;
import ru.academits.lapteva.shapes.Triangle;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Square(2));
        shapeList.add(new Rectangle(2, 4));
        shapeList.add(new Rectangle(5, 4));
        shapeList.add(new Circle(2));
        shapeList.add(new Circle(100));
        shapeList.add(new Triangle(1, 2, 10, 1, 4, 2));

        printShapeWithMaxArea(shapeList);
        printShapeWithSecondMaxPerimeter(shapeList);

        System.out.println("Хэш-код для фигуры " + shapeList.get(0) + ": " + shapeList.get(0).hashCode());
        System.out.println("Хэш-код для фигуры " + shapeList.get(3) + ": " + shapeList.get(3).hashCode());
    }

    public static void printShapeWithMaxArea(ArrayList<Shape> shapeList) {
        Collections.sort(shapeList, new AreaComparator());

        System.out.println("Фигура с наибольшей площадью:");
        System.out.println(shapeList.get(shapeList.size() - 1));
        System.out.println("площадью " + shapeList.get(shapeList.size() - 1).getArea());
        System.out.println("и периметром " + shapeList.get(shapeList.size() - 1).getPerimeter());
        System.out.println();
    }

    public static void printShapeWithSecondMaxPerimeter(ArrayList<Shape> shapeList) {
        Collections.sort(shapeList, new PerimeterComparator());

        System.out.println("Фигура со вторым по величине периметром:");
        System.out.println(shapeList.get(shapeList.size() - 2));
        System.out.println("площадью " + shapeList.get(shapeList.size() - 2).getArea());
        System.out.println("и периметром " + shapeList.get(shapeList.size() - 2).getPerimeter());
        System.out.println();
    }
}
