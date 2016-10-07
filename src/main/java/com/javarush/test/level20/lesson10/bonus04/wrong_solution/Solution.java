package com.javarush.test.level20.lesson10.bonus04.wrong_solution;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
should override:

clone()
size()
clear()
get(int index)
add(String value)
remove(...)
getParent(...)
iterator()
 */

public class Solution
        extends AbstractList<String> implements List<String>, Cloneable, Serializable {

    private Node root = new Node(null);

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return root.countNodes(root) - 1;
    }

    @Override
    public boolean add(String value) {
        Node slot = new Node(value);
        int level = root.findFreeLevel();
        return root.pasteOnLevel(level, slot);
    }

    @Override
    public boolean remove(Object o) {
        String value = (String)o;
        Node foundNode = root.findNode(root, value);
        Node parent = foundNode.prev;
        if (parent.left.equals(foundNode)) {
            parent.left = null;
        }
        else if (parent.right.equals(foundNode)) {
            parent.right = null;
        }

        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return new MyIterator(this);
    }

    public String getParent(String value) {
        Node nodeWithValue = root.findNode(root, value);
        Node parent = nodeWithValue != null ? nodeWithValue.prev : null;

        return parent != null ? parent.value : null;
    }

    private static class Node {
        String value;
        Node prev;
        Node left;
        Node right;

        Node(String value) {
            Node.this.value = value;
        }

        public int countNodes(Node node) {
            if (node == null) return 0;
            int count = 1;

            if (node.left != null) {
                count += countNodes(node.left);
            }

            if (node.right != null) {
                count += countNodes(node.right);
            }

            return count;
        }

        public int findFreeLevel() {
            int level = 0;

            while (!isLevelFree(level)) {
                level++;
            }
            return level;
        }

        public boolean isLevelFree(int level) {
            if (level < 1) return false;

            List<Node> levelNodes = getLevelNodes(level);
            int fullLevelCount = (int)Math.pow(2, level);

            return levelNodes.size() < fullLevelCount;
        }

        private List<Node> getLevelNodes(int level) {
            List<Node> levelNodes = new ArrayList<>();
            levelNodes.add(Node.this);

            while (level != 0) {
                level--;
                List<Node> tNodes = new ArrayList<>();
                for (Node levelNode : levelNodes) {
                    if (levelNode.left == null) {
                        if (level != 0) throw new IllegalArgumentException();
                    } else tNodes.add(levelNode.left);

                    if (levelNode.right == null) {
                        if (level != 0) throw new IllegalArgumentException();
                    } else tNodes.add(levelNode.right);
                }
                levelNodes = tNodes;
            }

            return levelNodes;
        }

        public Node findNode(Node node, String value) {
            if (node == null) return null;

            String nodeValue = node.value;
            if (nodeValue == value) return node;
            if (nodeValue != null && value != null && nodeValue.equals(value)) return node;

            Node node1 = findNode(node.left, value);
            Node node2 = findNode(node.right, value);
            if (node1 != null) return node1;
            if (node2 != null) return node2;

            return null;
        }

        public boolean pasteOnLevel(int level, Node toPaste) {
            if (level == 0) return false;

            List<Node> prevLevel = getLevelNodes(level - 1);
            for (Node node : prevLevel) {
                if (node.left == null) {
                    node.left = toPaste;
                    node.left.prev = node;
                    return true;
                }
                else if (node.right == null) {
                    node.right = toPaste;
                    node.right.prev = node;
                    return true;
                }
            }

            return false;
        }
    }

    private static class MyIterator implements Iterator<String> {
        Node root;
        Node currentNode;
        int level;
        List<Node> levelNodes;

        public MyIterator(Solution solution) {
            root = solution.root;
            level = 1;
            levelNodes = root.getLevelNodes(level);
        }

        @Override
        public boolean hasNext() {
            if (levelNodes.isEmpty()) {
                level++;
                levelNodes = root.getLevelNodes(level);
            }

            if (!levelNodes.isEmpty()) return true;
            return false;
        }

        @Override
        public String next() {
            if (hasNext()) {
                currentNode = levelNodes.remove(0);
                return currentNode.value;
            }
            return null;
        }

        @Override
        public void remove() {
            Node parent = currentNode.prev;
            if (parent.left.equals(currentNode)) {
                parent.left = null;
            }
            else if (parent.right.equals(currentNode)) {
                parent.right = null;
            }
        }
    }

    public static void main(String[] args) {
        //test1
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
        //test2
        List<String> list2 = new Solution();
        Node root = ((Solution) list2).root;
        for (int i = 1; i < 16; i++) {
            list2.add(String.valueOf(i));
        }
        list2.remove("2");
        list2.remove("9");
        //add 16,17,18,19,20
        for (int i = 16; i < 21; i++) {
            list2.add(String.valueOf(i));
        }
        list2.remove("18");
        list2.remove("20");
        list2.add("21");
        list2.add("22");
    }
}
