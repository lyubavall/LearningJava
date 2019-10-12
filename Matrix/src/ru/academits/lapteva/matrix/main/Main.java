package ru.academits.lapteva.matrix.main;

import ru.academits.lapteva.matrix.Matrix;
import ru.academits.lapteva.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 2);

        double[][] components = {{1}, {2, 3}};
        Matrix m2 = new Matrix(components);

        Vector v1 = new Vector(1);
        Vector v2 = new Vector(new double[]{-1, -1});
        Matrix m3 = new Matrix(new Vector[]{v1, v2});

        double[][] comp = {{1, 2, 3, -3}, {2, 1, 1, -2}, {3, 2, 2, 5}, {2, 3, 1, -2}};
        Matrix m4 = new Matrix(comp);

        Matrix m5 = new Matrix(m3);

        System.out.println("Размер матрицы " + m1 + " :");
        System.out.println("Высота " + m1.getHeight() + ", Ширина " + m1.getWidth());
        m2.setVector(0, v1);
        System.out.println("Для матрицы " + m2 + " получить установленный вектор: " + m2.getVector(0));
        System.out.println("Получить вектор-столбец: " + m2.getVectorFromColumn(0));
        System.out.println("Транспонирование: " + m2.transpose());
        System.out.println("Умножение на скаляр " + m2.multiplyByCoefficient(2));
        System.out.println("Умножение на вектор " + m2.multiplyByVector(v2));
        System.out.println("Сумма матриц " + m1.getSum(m2));
        System.out.println("Разница матриц " + m1.getDifference(m2));
        System.out.println("Определитель матрицы: " + m4.getDeterminant());
        System.out.println();

        System.out.println("Статические методы:");
        System.out.println("Умножение матриц " + m2 + " и " + m5 + ":");
        System.out.println(Matrix.multiply(m2, m5));
        System.out.println("Сумма матриц: " + Matrix.getSum(m2, m5));
        System.out.println(m2 + " и " + m5);
        System.out.println("Разница матриц: " + Matrix.getDifference(m2, m5));
    }
}

