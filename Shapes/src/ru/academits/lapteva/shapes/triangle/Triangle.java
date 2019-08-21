package ru.academits.lapteva.shapes.triangle;

import ru.academits.lapteva.shapes.Shape;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.max(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.max(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double side1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double side2 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        double halfPerimeter = (side1 + side2 + side3) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
    }

    @Override
    public double getPerimeter() {
        double side1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double side2 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Треугольник с высотой  " + getHeight() + " и шириной " + getWidth();
    }

    @Override
    public boolean equals(Object s) {
        if (s == this) {
            return true;
        }

        if (s == null || s.getClass() != this.getClass()) {
            return false;
        }

        Triangle t = (Triangle) s;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}
