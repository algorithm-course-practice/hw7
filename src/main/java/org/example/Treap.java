package org.example;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Теория<br/>
 * <a href="http://e-maxx.ru/algo/treap">http://e-maxx.ru/algo/treap</a><br/>
 * <a href="https://www.geeksforgeeks.org/treap-a-randomized-binary-search-tree/">https://www.geeksforgeeks.org/treap-a-randomized-binary-search-tree/</a><br/>
 * <a href="https://www.geeksforgeeks.org/implementation-of-search-insert-and-delete-in-treap/">https://www.geeksforgeeks.org/implementation-of-search-insert-and-delete-in-treap/</a><br/>
 * <a href="http://faculty.washington.edu/aragon/pubs/rst89.pdf">http://faculty.washington.edu/aragon/pubs/rst89.pdf</a><br/>
 * <a href="https://habr.com/ru/articles/101818/">https://habr.com/ru/articles/101818/</a><br/>
 * Примеение в linux kernel<br/>
 * <a href="https://www.kernel.org/doc/mirror/ols2005v2.pdf">https://www.kernel.org/doc/mirror/ols2005v2.pdf</a>
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Treap<K extends Comparable<K>> {

    Node<K> root;

    public void add(K key) {
        root = insert(root, key);
    }

    public Node<K> findNodeByIndex(int index) {
        Node[] res = new Node[1];
        findNodeByIndex(root, new AtomicInteger(0), res, index);
        return res[0];
    }

    public void findNodeByIndex(Node<K> cur, AtomicInteger index, Node[] findedNode, int searchIndex) {
        if (cur == null || findedNode[0] != null) {
            return;
        }
        findNodeByIndex(cur.left, index, findedNode, searchIndex);
        if(index.getAndIncrement() == searchIndex) {
            findedNode[0] = cur;

        }
        findNodeByIndex(cur.right, index, findedNode, searchIndex);
    }

    public void removeNode(Node<K> removedNode) {
        root = deleteNode(root, removedNode.key);
    }


    private Node<K> deleteNode(Node<K> cur, K key) {
        if (cur == null)
            return cur;

        if (key.compareTo(cur.key) < 0)
            cur.left = deleteNode(cur.left, key);
        else if (key.compareTo(cur.key) > 0)
            cur.right = deleteNode(cur.right, key);

            // IF KEY IS AT ROOT

            // If left is NULL
        else if (cur.left == null) {
            cur = cur.right;  // Make right child as root
        }
        // If Right is NULL
        else if (cur.right == null) {
            cur = cur.left;  // Make left child as root
        }
        // If key is at root and both left and right are not NULL
        else if (cur.left.priority < cur.right.priority) {
            cur = leftRotation(cur);
            cur.left = deleteNode(cur.left, key);
        } else {
            cur = rightRotation(cur);
            cur.right = deleteNode(cur.right, key);
        }

        return cur;
    }

    private Node<K> insert(Node<K> cur, K key) {
        if (cur == null) {
            return new Node<>(key);
        }
        if (key.compareTo(cur.key) > 0) {
            cur.right = insert(cur.right, key);
            if (cur.right.priority < cur.priority) {
                cur = leftRotation(cur);
            }

        } else {
            cur.left = insert(cur.left, key);
            if (cur.left.priority < cur.priority) {
                cur = rightRotation(cur);
            }

        }
        return cur;
    }

    /* T1, T2 and T3 are subtrees of the tree rooted with y
  (on left side) or x (on right side)
                y                               x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3 */

    private Node<K> leftRotation(Node<K> x) {
        Node<K> y = x.right;
        Node<K> T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    private Node<K> rightRotation(Node<K> y) {
        Node<K> x = y.left;
        Node<K> T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }


    @Getter
    public static class Node<K extends Comparable<K>> {
        static Random RND = new Random();
        K key;
        int priority;
        Node<K> left;
        Node<K> right;

        public Node(K key) {
            this(key, RND.nextInt());
        }

        public Node(K key, int priority) {
            this(key, priority, null, null);
        }

        public Node(K key, int priority, Node<K> left, Node<K> right) {
            this.key = key;
            this.priority = priority;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("(%s,%d)", key, priority);
        }
    }
}
