package ru.academits.lapteva.tree.main;

import ru.academits.lapteva.tree.BinaryTree;

public class Main {
    public static void main(String[] arg) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(2);
        tree.add(7);
        tree.add(5);
        tree.add(2);
        tree.add(6);
        tree.add(9);
        tree.add(5);
        tree.add(11);
        tree.add(4);

        tree.isEmpty();

        System.out.println("Поиск числа 13 " + tree.contains(13));
        System.out.println("Число элементов: " + tree.getElementsCount());

        System.out.println("Обход в ширину:");
        tree.breadthTraversal(System.out::println);

        System.out.println("Обход в глубину рекурсивно:");
        tree.recursiveDepthTraversal(tree.getRoot(), System.out::println);

        System.out.println("Обход в глубину:");
        tree.depthTraversal(System.out::println);

        System.out.println(tree.removeFirstOccurrence(2));
        System.out.println("Обход в ширину:");
        tree.breadthTraversal(System.out::println);
    }
}
