package ru.academits.lapteva.shapes.comparators;

import ru.academits.lapteva.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getPerimeter(), s2.getPerimeter());
    }
}
