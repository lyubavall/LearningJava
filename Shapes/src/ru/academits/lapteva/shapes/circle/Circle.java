package ru.academits.lapteva.shapes.circle;

import ru.academits.lapteva.shapes.Shape;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Круг с радиусом  " + radius;
    }

    @Override
    public boolean equals(Object s) {
        if (s == this) {
            return true;
        }

        if (s == null || s.getClass() != this.getClass()) {
            return false;
        }

        Circle c = (Circle) s;
        return radius == c.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}
