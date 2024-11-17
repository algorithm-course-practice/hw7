package org.example;

import lombok.Getter;

@Getter
public class Node {
    public int value;
    public int priority;
    public int size;
    public Node left;
    public Node right;

    public Node(int valueParam, int priorityParam) {
        value = valueParam;
        priority = priorityParam;
        size = 1;
        left = null;
        right = null;
    }

    /**
     * updateSize -> updates the subtree size of the current node
     */
    void updateSize() {
        size = 1;
        if (left != null) {
            size += left.size;
        }
        if (right != null) {
            size += right.size;
        }
    }
}
