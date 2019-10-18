package ru.academits.lapteva.matrix;

import ru.academits.lapteva.vector.Vector;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rowsCount, int columnsCount) {
        matrixRows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            matrixRows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        matrixRows = new Vector[matrix.matrixRows.length];

        for (int i = 0; i < matrix.matrixRows.length; ++i) {
            matrixRows[i] = new Vector(matrix.matrixRows[i]);
        }
    }

    public Matrix(double[][] components) {
        int maxLength = 0;

        for (double[] row : components) {
            maxLength = Math.max(maxLength, row.length);
        }

        matrixRows = new Vector[components.length];

        for (int i = 0; i < components.length; ++i) {
            matrixRows[i] = new Vector(maxLength);

            for (int j = 0; j < components[i].length; ++j) {
                matrixRows[i].setComponent(j, components[i][j]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        int maxLength = 0;

        for (Vector vector : vectors) {
            maxLength = Math.max(maxLength, vector.getSize());
        }

        matrixRows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            matrixRows[i] = new Vector(maxLength, vectors[i].getComponents());
        }
    }

    public int getRowsCount() {
        return matrixRows.length;
    }

    public int getColumnsCount() {
        return matrixRows[0].getSize();
    }

    public Vector getRow(int index) {
        indexValidation(index);

        return new Vector(matrixRows[index]);
    }

    public void setRow(int index, Vector row) {
        indexValidation(index);

        matrixRows[index] = new Vector(this.getColumnsCount(), row.getComponents());
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы массива");
        }

        Vector column = new Vector(matrixRows.length);

        for (int i = 0; i < column.getSize(); ++i) {
            column.setComponent(i, matrixRows[i].getComponent(index));
        }

        return column;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= matrixRows.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы массива");
        }
    }

    public Matrix transpose() {
        Vector[] buffer = new Vector[this.getColumnsCount()];

        for (int i = 0; i < buffer.length; ++i) {
            buffer[i] = new Vector(this.getColumn(i));
        }

        matrixRows = buffer.clone();

        for (int i = 0; i < buffer.length; ++i) {
            matrixRows[i] = new Vector(buffer[i]);
        }

        return this;
    }

    public Matrix multiplyByCoefficient(double coefficient) {
        for (int i = 0; i < matrixRows.length; ++i) {
            this.setRow(i, matrixRows[i].multiplyByCoefficient(coefficient));
        }

        return this;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Вектор неправильной размерности");
        }

        Vector resultedVector = new Vector(matrixRows.length);

        for (int i = 0; i < resultedVector.getSize(); ++i) {
            resultedVector.setComponent(i, Vector.getScalarMultiplication(matrixRows[i], vector));
        }

        return resultedVector;
    }

    private void matrixSizeValidation(Matrix matrix) {
        if (matrix.getRowsCount() != matrixRows.length || matrix.getColumnsCount() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Размер матриц не совпадает");
        }
    }

    public Matrix getSum(Matrix matrix) {
        matrixSizeValidation(matrix);

        for (int i = 0; i < matrixRows.length; ++i) {
            matrixRows[i] = matrixRows[i].getSum(matrix.matrixRows[i]);
        }

        return this;
    }

    public Matrix getDifference(Matrix matrix) {
        matrixSizeValidation(matrix);

        for (int i = 0; i < matrixRows.length; ++i) {
            matrixRows[i] = matrixRows[i].getDifference(matrix.matrixRows[i]);
        }

        return this;
    }

    public double getDeterminant() {
        if (matrixRows.length != matrixRows[0].getSize()) {
            throw new IllegalArgumentException("Матрица не квадратная");
        }

        double determinant = 0;
        int size = matrixRows.length;

        if (size == 1) {
            determinant = matrixRows[0].getComponent(0);
        } else if (size == 2) {
            determinant = matrixRows[0].getComponent(0) * matrixRows[1].getComponent(1) - matrixRows[0].getComponent(1) * matrixRows[1].getComponent(0);
        } else {
            Matrix minor = new Matrix(size - 1, size - 1);

            for (int i = 0; i < size; ++i) {
                int minorI = 0;

                for (int j = 1; j < size; ++j) {
                    int minorJ = 0;

                    for (int k = 0; k < size; ++k) {
                        if (k == i) {
                            continue;
                        }

                        minor.matrixRows[minorI].setComponent(minorJ, matrixRows[j].getComponent(k));
                        ++minorJ;
                    }

                    ++minorI;
                }

                determinant += matrixRows[0].getComponent(i) * Math.pow(-1, i) * minor.getDeterminant();
            }
        }

        return determinant;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.matrixSizeValidation(matrix2);

        return new Matrix(matrix1).getSum(matrix2);
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.matrixSizeValidation(matrix2);

        return new Matrix(matrix1).getDifference(matrix2);
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix m3 = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < m3.getRowsCount(); ++i) {
            for (int j = 0; j < m3.getColumnsCount(); ++j) {
                m3.matrixRows[i].setComponent(j, Vector.getScalarMultiplication(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }

        return m3;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{ ").append(matrixRows[0]);

        for (int i = 1; i < matrixRows.length; ++i) {
            s.append(", ").append(matrixRows[i]);
        }

        return s.append(" }").toString();
    }
}
