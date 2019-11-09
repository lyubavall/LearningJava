package ru.academits.lapteva.matrix;

import ru.academits.lapteva.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Количество строк и количество столбцов должны быть больше нуля");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] components) {
        if (components == null) {
            throw new IllegalArgumentException("Массив не должен быть пустой ссылкой");
        }

        if (components.length == 0) {
            throw new IllegalArgumentException("В массиве ноль строк");
        }

        int maxLength = 0;

        for (double[] row : components) {
            maxLength = Math.max(maxLength, row.length);
        }

        if (maxLength == 0) {
            throw new IllegalArgumentException("В массиве все строки нулевой длины");
        }

        rows = new Vector[components.length];

        for (int i = 0; i < components.length; ++i) {
            rows[i] = new Vector(maxLength, components[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("В массиве ноль векторов");
        }

        int maxLength = 0;

        for (Vector vector : vectors) {
            maxLength = Math.max(maxLength, vector.getSize());
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(maxLength);

            for (int j = 0; j < vectors[i].getSize(); ++j) {
                rows[i].setComponent(j, vectors[i].getComponent(j));
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void validateRowIndex(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс строки должен быть в пределах от 0 до " + (rows.length - 1));
        }
    }

    public Vector getRow(int rowIndex) {
        validateRowIndex(rowIndex);

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        validateRowIndex(rowIndex);

        if (row.getSize() > this.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора не должна быть больше, чем число столбцов в матрице");
        }

        rows[rowIndex] = new Vector(this.getColumnsCount());

        for (int i = 0; i < row.getSize(); ++i) {
            rows[rowIndex].setComponent(i, row.getComponent(i));
        }
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= this.getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс столбца должен быть в пределах от 0 до " + (this.getColumnsCount() - 1));
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < column.getSize(); ++i) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public void transpose() {
        Vector[] newRows = new Vector[this.getColumnsCount()];

        for (int i = 0; i < newRows.length; ++i) {
            newRows[i] = this.getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByCoefficient(double coefficient) {
        for (Vector row : rows) {
            row.multiplyByCoefficient(coefficient);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Вектор неправильной размерности");
        }

        Vector resultedVector = new Vector(rows.length);

        for (int i = 0; i < resultedVector.getSize(); ++i) {
            resultedVector.setComponent(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return resultedVector;
    }

    private void checkSizesEquality(Matrix matrix) {
        if (matrix.getRowsCount() != this.getRowsCount() || matrix.getColumnsCount() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Размер матриц не совпадает");
        }
    }

    public void add(Matrix matrix) {
        checkSizesEquality(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].getSum(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizesEquality(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].getDifference(matrix.rows[i]);
        }
    }

    public double getDeterminant() {
        if (rows.length != this.getColumnsCount()) {
            throw new IllegalArgumentException("Матрица не квадратная");
        }

        double determinant = 0;
        int size = rows.length;

        if (size == 1) {
            determinant = rows[0].getComponent(0);
        } else if (size == 2) {
            determinant = rows[0].getComponent(0) * rows[1].getComponent(1) - rows[0].getComponent(1) * rows[1].getComponent(0);
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

                        minor.rows[minorI].setComponent(minorJ, rows[j].getComponent(k));
                        ++minorJ;
                    }

                    ++minorI;
                }

                determinant += rows[0].getComponent(i) * Math.pow(-1, i) * minor.getDeterminant();
            }
        }

        return determinant;
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizesEquality(matrix2);

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizesEquality(matrix2);

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < newMatrix.getRowsCount(); ++i) {
            for (int j = 0; j < newMatrix.getColumnsCount(); ++j) {
                newMatrix.rows[i].setComponent(j, Vector.getScalarMultiplication(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{ ").append(rows[0]);

        for (int i = 1; i < rows.length; ++i) {
            s.append(", ").append(rows[i]);
        }

        return s.append(" }").toString();
    }
}
