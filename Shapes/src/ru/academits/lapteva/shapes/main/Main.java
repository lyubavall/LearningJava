package ru.academits.lapteva.shapes.main;

import ru.academits.lapteva.shapes.Shape;
import ru.academits.lapteva.shapes.circle.Circle;
import ru.academits.lapteva.shapes.comparators.AreaComparator;
import ru.academits.lapteva.shapes.comparators.PerimeterComparator;
import ru.academits.lapteva.shapes.rectangle.Rectangle;
import ru.academits.lapteva.shapes.square.Square;
import ru.academits.lapteva.shapes.triangle.Triangle;

import java.util.Arrays;
import java.util.ArrayList;

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
        Shape[] shapeArray = shapeList.toArray(new Shape[shapeList.size()]);
        Arrays.sort(shapeArray, new AreaComparator());

        System.out.println("Фигура с наибольшей площадью:");
        System.out.println(shapeArray[shapeArray.length - 1]);
        System.out.println("площадью " + shapeArray[shapeArray.length - 1].getArea());
        System.out.println("и периметром " + shapeArray[shapeArray.length - 1].getPerimeter());
        System.out.println();
    }

    public static void printShapeWithSecondMaxPerimeter(ArrayList<Shape> shapeList) {
        Shape[] shapeArray = shapeList.toArray(new Shape[shapeList.size()]);
        Arrays.sort(shapeArray, new PerimeterComparator());

        System.out.println("Фигура со вторым по величине периметром:");
        System.out.println(shapeArray[shapeArray.length - 2]);
        System.out.println("площадью " + shapeArray[shapeArray.length - 2].getArea());
        System.out.println("и периметром " + shapeArray[shapeArray.length - 2].getPerimeter());
        System.out.println();
    }
}
