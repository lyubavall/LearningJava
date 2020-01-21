package ru.academits.lapteva.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int elementsCount;
    private Comparator comparator;

    public BinaryTree() {
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public boolean isEmpty() {
        return elementsCount == 0;
    }

    public T getRootData() {
        if (isEmpty()) {
            throw new NoSuchElementException("В дереве нет элементов");
        }

        return root.getData();
    }

    public void addRoot(T data) {
        root = new TreeNode<>(data);
        ++elementsCount;
    }

    private int treeCompare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        return ((Comparable<T>) data1).compareTo(data2);
    }

    public void add(T data) {
        if (isEmpty()) {
            addRoot(data);
            return;
        }

        TreeNode<T> node = root;

        while (node != null) {
            if (treeCompare(data, node.getData()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    node.setLeft(new TreeNode<>(data));
                    ++elementsCount;
                    return;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    node.setRight(new TreeNode<>(data));
                    ++elementsCount;
                    return;
                }
            }
        }
    }

    public boolean contains(T data) {
        TreeNode<T> node = root;

        while (node != null) {
            if (treeCompare(data, node.getData()) == 0) {
                return true;
            }

            if (treeCompare(data, node.getData()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    break;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    break;
                }
            }
        }

        return false;
    }

    public boolean removeFirstOccurrence(T data) {
        if (isEmpty()) {
            return false;
        }

        TreeNode<T> node = root;
        TreeNode<T> parent = null;
        boolean isLeft = false;

        while (node != null) {
            if (treeCompare(data, node.getData()) == 0) {
                if (elementsCount == 1) {
                    root = null;
                    return true;
                }

                break;
            }

            parent = node;

            if (treeCompare(data, node.getData()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    isLeft = true;
                } else {
                    return false;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                    isLeft = false;
                } else {
                    return false;
                }
            }
        }

        assert node != null;
        if (node.getRight() == null && node.getLeft() == null) {
            if (isLeft) {
                parent.setLeft(null);
            } else {
                assert parent != null;
                parent.setRight(null);
            }

            --elementsCount;
            return true;
        }

        if (node.getLeft() == null) {
            if (parent == null) {
                root = node.getRight();
            } else if (isLeft) {
                parent.setLeft(node.getRight());
            } else {
                parent.setRight(node.getRight());
            }

            --elementsCount;
            return true;
        } else if (node.getRight() == null) {
            if (parent == null) {
                root = node.getLeft();
            } else if (isLeft) {
                parent.setLeft(node.getLeft());
            } else {
                parent.setRight(node.getLeft());
            }

            --elementsCount;
            return true;
        }

        TreeNode<T> leftmost = node.getRight();
        TreeNode<T> leftmostParent = null;

        while (leftmost.getLeft() != null) {
            leftmostParent = leftmost;
            leftmost = leftmost.getLeft();
        }

        if (leftmostParent != null) {
            leftmostParent.setLeft(leftmost.getRight());
            leftmost.setRight(node.getRight());
        }

        leftmost.setLeft(node.getLeft());

        if (parent == null) {
            root = leftmost;
        } else {
            if (isLeft) {
                parent.setLeft(leftmost);
            } else {
                parent.setRight(leftmost);
            }
        }

        --elementsCount;
        return true;
    }

    public void breadthTraversal(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.remove();
            consumer.accept(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void recursiveDepthTraversal(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());
        if (node.getRight() == null && node.getLeft() == null) {
            return;
        }

        if (node.getLeft() != null) {
            recursiveDepthTraversal(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            recursiveDepthTraversal(node.getRight(), consumer);
        }
    }

    public void depthTraversal(Consumer<T> consumer) {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.removeLast();
            consumer.accept(node.getData());
            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }
    }

    public TreeNode<T> getRoot() {
        if (isEmpty()) {
            throw new NoSuchElementException("В дереве нет элементов");
        }

        return root;
    }
}
