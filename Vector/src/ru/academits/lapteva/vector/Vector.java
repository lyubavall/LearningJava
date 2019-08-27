package ru.academits.lapteva.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] components;

    public Vector(int n) {
        this.n = n;
        components = new double[n];

        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }
    }

    public Vector(Vector vector) {
        n = vector.components.length;
        components = vector.components;

        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }
    }

    public Vector(double[] components) {
        n = components.length;
        this.components = components;

        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }
    }

    public Vector(int n, double[] components) {
        this.n = n;
        this.components = Arrays.copyOf(components, n);

        if (n <= 0) {
            throw new IllegalArgumentException("Вектор нулевой размерности");
        }
    }

    public int getSize() {
        return components.length;
    }

    public Vector getVectorsSum(Vector vector) {
        int resultSize = getResultSize(vector);

        for (int i = 0; i < resultSize; ++i) {
            components[i] = components[i] + vector.components[i];
        }

        return this;
    }

    public Vector getVectorsDifference(Vector vector) {
        int resultSize = getResultSize(vector);

        for (int i = 0; i < resultSize; ++i) {
            components[i] = components[i] - vector.components[i];
        }

        return this;
    }

    private int getResultSize(Vector vector) {
        int resultSize = n;

        if (n > vector.getSize()) {
            vector.components = Arrays.copyOf(vector.components, resultSize);
        } else if (n < vector.getSize()) {
            resultSize = vector.getSize();
            components = Arrays.copyOf(components, resultSize);
        }
        return resultSize;
    }

    public Vector multiplyVectorScalarly(double k) {
        for (int i = 0; i < n; ++i) {
            components[i] = components[i] * k;
        }

        return this;
    }

    public Vector reflectVector() {
        for (int i = 0; i < n; ++i) {
            components[i] = components[i] * (-1);
        }

        return this;
    }

    public double getVectorLength() {
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


    public static Vector getVectorsSum(Vector v1, Vector v2) {
        int resultSize = getResultSize(v1, v2);
        double[] resultComponents = new double[resultSize];

        for (int i = 0; i < resultSize; ++i) {
            resultComponents[i] = v1.components[i] + v2.components[i];
        }

        return new Vector(resultComponents);
    }

    public static Vector getVectorsDifference(Vector v1, Vector v2) {
        int resultSize = getResultSize(v1, v2);
        double[] resultComponents = new double[resultSize];

        for (int i = 0; i < resultSize; ++i) {
            resultComponents[i] = v1.components[i] - v2.components[i];
        }

        return new Vector(resultComponents);
    }

    private static int getResultSize(Vector v1, Vector v2) {
        int resultSize = v1.getSize();

        if (v1.getSize() > v2.getSize()) {
            v2.components = Arrays.copyOf(v2.components, resultSize);
        } else if (v1.getSize() < v2.getSize()) {
            resultSize = v2.getSize();
            v1.components = Arrays.copyOf(v1.components, resultSize);
        }
        return resultSize;
    }

    public static double getScalarMultiplication(Vector v1, Vector v2) {
        int resultSize = getResultSize(v1, v2);
        double scalarMultiplication = 0;

        for (int i = 0; i < resultSize; ++i) {
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
        return (components.length == v.components.length && Arrays.equals(components, v.components));
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Integer.hashCode(n);

        for (double e : components) {
            hash = prime * hash + Double.hashCode(e);
        }

        return hash;
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }
}
