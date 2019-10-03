package ru.academits.lapteva.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = vector.components.clone();
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }

        this.components = components.clone();
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }

        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    public Vector getSum(Vector vector) {
        int vSize = vector.getSize();

        if (components.length < vSize) {
            components = Arrays.copyOf(components, vSize);
        }

        for (int i = 0; i < vSize; ++i) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector getDifference(Vector vector) {
        int vSize = vector.getSize();

        if (components.length < vSize) {
            components = Arrays.copyOf(components, vSize);
        }

        for (int i = 0; i < vSize; ++i) {
            components[i] -= vector.components[i];
        }

        return this;
    }

    public Vector multiplyByCoefficient(double k) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= k;
        }

        return this;
    }

    public Vector reverse() {
        multiplyByCoefficient(-1);
        return this;
    }

    public double getLength() {
        double quadraticSum = 0;

        for (double e : components) {
            quadraticSum += e * e;
        }

        return Math.sqrt(quadraticSum);
    }

    public double getComponent(int i) {
        return components[i];
    }

    public void setComponent(int i, double component) {
        components[i] = component;
    }

    public static Vector getSum(Vector v1, Vector v2) {
        return new Vector(v1).getSum(v2);
    }

    public static Vector getDifference(Vector v1, Vector v2) {
        return new Vector(v1).getDifference(v2);
    }

    public static double getScalarMultiplication(Vector v1, Vector v2) {
        double scalarMultiplication = 0;
        int vSize = Math.min(v1.getSize(), v2.getSize());

        for (int i = 0; i < vSize; ++i) {
            scalarMultiplication += v1.components[i] * v2.components[i];
        }

        return scalarMultiplication;
    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || this.getClass() != vector.getClass()) {
            return false;
        }

        Vector v = (Vector) vector;
        return (Arrays.equals(components, v.components));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{ ").append(components[0]);

        for (int i = 1; i < components.length; ++i) {
            s.append(", ").append(components[i]);
        }

        return String.valueOf(s.append(" }"));
    }
}
