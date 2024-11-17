package org.example;

import org.example.printer.TreePrinter;

import java.util.Random;


public class Treap {
    /**
     * root -> удерживает корневой узел в Treap
     * random -> чтобы сгенерировать случайный приоритет для узлов в ловушке Treap
     */
    private Node root;
    private Random random = new Random();

    private TreePrinter<Node> printer;

    /**
     * Constructors
     * <p>
     * Treap() -> create an empty Treap
     * Treap(int[] nodeValues) -> add the elements given in the array to the Treap
     */
    public Treap() {
        root = null;
        printer = new TreePrinter<>(Node::toString, Node::getLeft, Node::getRight);
        printer.setSquareBranches(true);
        printer.setTspace(1);
        printer.setHspace(1);
    }

    public void print() {
        printer.printTree(root);
    }

    /**
     * объединяет две ветки, левую и правую, в одну дерево
     *
     * @param left  left Treap
     * @param right right Treap
     * @return root of merged Treap
     */
    private Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            left.updateSize();
            return left;
        } else {
            right.left = merge(left, right.left);
            right.updateSize();
            return right;
        }
    }

    /**
     * разделите дерево на две: левая ветка содержит узлы <= ключ, а правая ветка содержит узлы > ключ.
     *
     * @param node корневой узел, подлежащий разделению
     * @param key  ключ для сравнения узлов
     * @return Массив узлов размером 2.
     * Node[0] содержит корень левой части после разделения
     * Node[1] содержит корень правой части после разделения
     */
    private Node[] split(Node node, int key) {
        if (node == null) {
            return new Node[]{null, null};
        }

        Node[] result;

        if (node.value <= key) {
            result = split(node.right, key);
            node.right = result[0];
            node.updateSize();
            result[0] = node;
        } else {
            result = split(node.left, key);
            node.left = result[1];
            node.updateSize();
            result[1] = node;
        }

        return result;
    }

    /**
     * вставка узла в Treap
     *
     * @param value значение, которое должно быть вставлено в Treap
     * @return корень дерева, в которай вставляется значение
     */
    public Node insert(int value) {
        if (root == null) {
            root = new Node(value, random.nextInt());
            return root;
        }

        Node[] splitted = split(root, value);

        Node node = new Node(value, random.nextInt());

        Node tempMerged = merge(splitted[0], node);
        tempMerged.updateSize();

        Node merged = merge(tempMerged, splitted[1]);
        merged.updateSize();

        root = merged;

        return root;
    }

    /**
     * Удаляем узел со значением value
     *
     * @param value значение, которое должно быть удалено
     * @return корень дерева, в которой было выполнено удаление
     */
    public Node delete(int value) {
        root = deleteNode(root, value);
        return root;
    }

    private Node deleteNode(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            root = merge(root.left, root.right);
        }

        if (root != null) {
            root.updateSize();
        }
        return root;
    }

    /**
     * Найдите значение в Treap
     *
     * @param value значение, которое необходимо найти
     * @return узел, содержащий значение
     * null if not found
     */
    public Node search(int value) {
        return searchVal(root, value);
    }

    private Node searchVal(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        } else if (root.value < value) {
            return searchVal(root.right, value);
        } else {
            return searchVal(root.left, value);
        }
    }

    /**
     * найдите нижнюю границу значения в Treap
     *
     * @param value значение, для которого должна быть найдена нижняя граница
     * @return узел, который является нижней границей переданного значения
     */
    public Node lowerBound(int value) {
        Node lowerBoundNode = null;
        Node current = root;

        while (current != null) {
            if (current.value >= value) {
                lowerBoundNode = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return lowerBoundNode;
    }

    /**
     * Находим верхнюю границу в Treap
     *
     * @param value значение, для которого должна быть найдена верхняя граница
     * @return узел, который является верхней границей переданного значения
     */
    public Node upperBound(int value) {
        Node upperBoundNode = null;
        Node current = root;

        while (current != null) {
            if (current.value > value) {
                upperBoundNode = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return upperBoundNode;
    }


}
