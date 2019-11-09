package ru.academits.lapteva.matrix.main;

import ru.academits.lapteva.matrix.Matrix;
import ru.academits.lapteva.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 2);

        double[][] components1 = {{1}, {2, 3}};
        Matrix matrix2 = new Matrix(components1);

        Vector vector1 = new Vector(1);
        Vector vector2 = new Vector(new double[]{-1, -1});
        Matrix matrix3 = new Matrix(new Vector[]{vector1, vector2});

        double[][] components2 = {{1, 2, 3, -3}, {2, 1, 1, -2}, {3, 2, 2, 5}, {2, 3, 1, -2}};
        Matrix matrix4 = new Matrix(components2);

        Matrix matrix5 = new Matrix(matrix3);

        System.out.print("Размер матрицы " + matrix1 + ": ");
        System.out.println("Количество строк " + matrix1.getRowsCount() + ", Количество столбцов " + matrix1.getColumnsCount());

        matrix2.setRow(0, vector1);
        System.out.println("Для матрицы " + matrix2 + " получить установленный вектор: " + matrix2.getRow(0));
        System.out.println("Получить вектор-столбец: " + matrix2.getColumn(0));
        matrix2.transpose();
        System.out.println("Транспонирование: " + matrix2);
        matrix2.multiplyByCoefficient(2);
        System.out.println("Умножение на скаляр " + matrix2);
        System.out.println("Умножение на вектор " + matrix2.multiplyByVector(vector2));

        System.out.println("Для матриц " + matrix1 + " и " + matrix2);
        matrix1.add(matrix2);
        System.out.println("Сумма матриц " + matrix1);
        matrix1.subtract(matrix2);

        System.out.println("Разница матриц " + matrix1);
        System.out.println("Определитель матрицы: " + matrix4.getDeterminant());
        System.out.println();

        System.out.println("Статические методы:");
        System.out.println("Умножение матриц " + matrix2 + " и " + matrix5 + ":");
        System.out.println(Matrix.multiply(matrix2, matrix5));
        System.out.println("Сумма матриц: " + Matrix.add(matrix2, matrix5));
        System.out.println("Разница матриц: " + Matrix.subtract(matrix2, matrix5));
    }
}

