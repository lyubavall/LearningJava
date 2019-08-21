package ru.academits.lapteva.shapes.square;

import ru.academits.lapteva.shapes.Shape;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной  " + sideLength;
    }

    @Override
    public boolean equals(Object s) {
        if (s == this) {
            return true;
        }

        if (s == null || s.getClass() != this.getClass()) {
            return false;
        }

        Square sq = (Square) s;
        return sideLength == sq.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}
