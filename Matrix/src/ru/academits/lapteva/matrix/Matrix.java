package ru.academits.lapteva.matrix;

import ru.academits.lapteva.vector.Vector;

public class Matrix {
    private Vector[] vMatrix;

    public Matrix(int n, int m) {
        vMatrix = new Vector[n];

        for (int i = 0; i < n; ++i) {
            vMatrix[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        vMatrix = matrix.vMatrix.clone();
    }

    public Matrix(double[][] components) {
        int maxLength = 0;

        for (int i = 0; i < components.length; ++i) {
            maxLength = Math.max(maxLength, components[i].length);
        }

        vMatrix = new Vector[components.length];

        for (int i = 0; i < components.length; ++i) {
            double[] vComponents = new double[maxLength];

            for (int j = 0; j < components[i].length; ++j) {
                vComponents[j] = components[i][j];
            }

            vMatrix[i] = new Vector(vComponents);
        }
    }

    public Matrix(Vector[] vectors) {
        int maxLength = 0;

        for (int i = 0; i < vectors.length; ++i) {
            maxLength = Math.max(maxLength, vectors[i].getSize());
        }

        vMatrix = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            vMatrix[i] = new Vector(maxLength, vectors[i].getComponents());
        }
    }

    public int getHeight() {
        return vMatrix.length;
    }

    public int getWidth() {
        return vMatrix[0].getSize();
    }

    public Vector getVector(int i) {
        if (i < 0 || i > vMatrix.length - 1) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        return vMatrix[i];
    }

    public void setVector(int i, Vector vector) {
        if (i < 0 || i > vMatrix.length - 1) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        vMatrix[i] = new Vector(vMatrix[0].getSize(), vector.getComponents());
    }

    public Vector getVectorFromColumn(int j) {
        if (j < 0 || j > vMatrix[j].getSize() - 1) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        Vector column = new Vector(vMatrix.length);

        for (int i = 0; i < column.getSize(); ++i) {
            column.setComponent(i, vMatrix[i].getComponent(j));
        }

        return column;
    }

    public Matrix transpose() {
        Matrix buffer = new Matrix(vMatrix[0].getSize(), vMatrix.length);

        for (int i = 0; i < buffer.vMatrix.length; ++i) {
            for (int j = 0; j < buffer.vMatrix[i].getSize(); ++j) {
                buffer.vMatrix[i].setComponent(j, vMatrix[j].getComponent(i));
            }
        }

        vMatrix = buffer.vMatrix.clone();
        return this;
    }

    public Matrix multiplyByCoefficient(double k) {
        for (int i = 0; i < vMatrix.length; ++i) {
            vMatrix[i] = vMatrix[i].multiplyByCoefficient(k);
        }

        return this;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != vMatrix[0].getSize()) {
            throw new IllegalArgumentException("Вектор неправильной размерности");
        }

        Vector resultedVector = new Vector(vector.getSize());

        for (int i = 0; i < vMatrix.length; ++i) {
            resultedVector.setComponent(i, Vector.getScalarMultiplication(vMatrix[i], vector));
        }
        return resultedVector;
    }

    public Matrix getSum(Matrix matrix) {
        if (matrix.getHeight() != vMatrix.length || matrix.getWidth() != vMatrix[0].getSize()) {
            throw new IllegalArgumentException("Размер матриц не совпадает");
        }

        for (int i = 0; i < vMatrix.length; ++i) {
            vMatrix[i] = Vector.getSum(this.vMatrix[i], matrix.vMatrix[i]);
        }

        return this;
    }

    public Matrix getDifference(Matrix matrix) {
        if (matrix.getHeight() != vMatrix.length || matrix.getWidth() != vMatrix[0].getSize()) {
            throw new IllegalArgumentException("Размер матриц не совпадает");
        }

        for (int i = 0; i < vMatrix.length; ++i) {
            vMatrix[i] = Vector.getDifference(this.vMatrix[i], matrix.vMatrix[i]);
        }

        return this;
    }

    public double getDeterminant() {
        if (vMatrix.length != vMatrix[0].getSize()) {
            throw new IllegalArgumentException("Матрица не квадратная");
        }

        double determinant = 0;
        int size = vMatrix.length;

        if (size == 1) {
            determinant = vMatrix[0].getComponent(0);
        } else if (size == 2) {
            determinant = vMatrix[0].getComponent(0) * vMatrix[1].getComponent(1) - vMatrix[0].getComponent(1) * vMatrix[1].getComponent(0);
        } else {
            Matrix minor = new Matrix(size - 1, size - 1);

            for (int s = 0; s < size; ++s) {
                int minorI = 0;

                for (int i = 1; i < size; ++i) {
                    int minorJ = 0;

                    for (int j = 0; j < size; ++j) {
                        if (j == s) {
                            continue;
                        }

                        minor.vMatrix[minorI].setComponent(minorJ, vMatrix[i].getComponent(j));
                        ++minorJ;
                    }

                    ++minorI;
                }

                determinant += vMatrix[0].getComponent(s) * Math.pow(-1, s) * minor.getDeterminant();
            }
        }

        return determinant;
    }

    public static Matrix getSum(Matrix m1, Matrix m2) {
        return new Matrix(m1).getSum(m2);
    }

    public static Matrix getDifference(Matrix m1, Matrix m2) {
        return new Matrix(m1).getDifference(m2);
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getWidth() != m2.getHeight()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix m3 = new Matrix(m1.getHeight(), m2.getWidth());

        for (int i = 0; i < m3.getHeight(); ++i) {
            for (int j = 0; j < m3.getWidth(); ++j) {
                m3.vMatrix[i].setComponent(j, Vector.getScalarMultiplication(m1.getVector(i), m2.getVectorFromColumn(j)));
            }
        }

        return m3;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{ ").append(vMatrix[0]);

        for (int i = 1; i < vMatrix.length; ++i) {
            s.append(", ").append(vMatrix[i]);
        }

        return s.append(" }").toString();
    }
}
