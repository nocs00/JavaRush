package com.javarush.test.level20.lesson10.bonus04;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
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
        return countNodes(root) - 1;
    }

    @Override
    public boolean add(String value) {
        Node node = new Node(value);
        int level = findLastLevel();
        return pasteOnLevel(level, node);
    }

    @Override
    public boolean remove(Object o) {
        String value = (String)o;
        Node node = findNode(root, value);
        if (node == null) return false;
        Node parent = node.prev;

        if (parent.left != null && parent.left.equals(node)) {
            parent.left = parent.right;
            parent.right = null;
            return true;
        }
        if (parent.right != null && parent.right.equals(node)) {
            parent.right = null;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return new MyIterator();
    }

    @Override
    public void clear() {
        root.left = null;
        root.right = null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution copy = new Solution();
        Iterator<String> iterator = this.iterator();
        while(iterator.hasNext()) copy.add(new String(iterator.next()));
        return copy;
    }

    public String getParent(String value) {
        Node nodeWithValue = findNode(root, value);
        Node parent = nodeWithValue != null ? nodeWithValue.prev : null;

        return parent != null ? parent.value : null;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.writeInt(size());
        out.writeObject(root.left != null ? root.left.value : null);
        out.writeObject(root.right != null ? root.right.value : null);

        Iterator<String> iterator = iterator();
        while(iterator.hasNext()) {
            String value = iterator.next();
            Node node = findNode(root, value);
            out.writeObject(value);
            out.writeObject(node.left != null ? node.left.value : null);
            out.writeObject(node.right != null ? node.right.value : null);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        int size = in.readInt();
        if (size <= 0) throw new IllegalArgumentException();

        String rootLeft = (String)in.readObject();
        String rootRight = (String)in.readObject();
        root = new Node(null, null,
                rootLeft != null ? new Node(rootLeft, root) : null,
                rootRight != null ? new Node(rootRight, root) : null
        );

        for (int i = 0; i < size; i++) {
            String value = (String)in.readObject();
            String left = (String)in.readObject();
            String right = (String)in.readObject();

            Node current = findNode(root, value);
            if (current == null) continue;
            current.left = left != null ? new Node(left, current) : null;
            current.right = right != null ? new Node(right, current) : null;
        }
    }

    private Node findNode(Node node, String value) {
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

    private int countNodes(Node node) {
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

    private int findLastLevel() {
        int level = 1;

        while (hasChildsOnLevel(level)) {
            level++;
        }

        if (!isLevelFilled(level-1)) return level - 1;

        return level;
    }

    private boolean isLevelFilled(int level) {
        if (level < 0) throw new IllegalArgumentException();
        if (level == 0) return true;
        List<Node> levelNodes = getLevelNodes(level-1);
        Node lastNode = levelNodes.get(levelNodes.size()-1);
        if (lastNode.right == null) return false;

        return true;
    }

    private boolean hasChildsOnLevel(int level) {
        if (level < 0) throw new IllegalArgumentException();
        List<Node> levelNodes = getLevelNodes(level-1);

        if (levelNodes == null || levelNodes.isEmpty()) return false;

        for (Node node : levelNodes) {
            if (node.left != null || node.right != null) return true;
        }
        return false;
    }

    private List<Node> getLevelNodes(int level) {
        List<Node> levelNodes = new ArrayList<>();
        levelNodes.add(root);

        while (level != 0) {
            level--;
            List<Node> tNodes = new ArrayList<>();
            for (Node levelNode : levelNodes) {
                if (levelNode.left != null) tNodes.add(levelNode.left);
                if (levelNode.right != null) tNodes.add(levelNode.right);
            }
            levelNodes = tNodes;
        }

        return levelNodes;
    }

    private boolean pasteOnLevel(int level, Node toPaste) {
        List<Node> prevLevel = getLevelNodes(level - 1);

        for (int i = prevLevel.size()-1; i >= 0; i--) {
            Node node = prevLevel.get(i);
            if (i != 0) {
                Node prevNode = prevLevel.get(i-1);
                if ((node.left == null && node.right == null)
                        && (prevNode.left == null || prevNode.right == null)) continue;
            }
            if (node.left == null) {
                node.left = toPaste;
                node.left.prev = node;
                return true;
            }
            if (node.right == null) {
                node.right = toPaste;
                node.right.prev = node;
                return true;
            }
        }

        return false;
    }

    private class MyIterator implements Iterator<String> {
        List<Node> allNodes = new ArrayList<>();
        private int nextIndex;
        private Node current;

        public MyIterator() {
            init();
            nextIndex = 0;
        }

        private void init() {
            int level = 1;
            List<Node> levelNodes = null;
            while (true) {
                levelNodes = getLevelNodes(level++);
                if (levelNodes.isEmpty()) break;
                allNodes.addAll(levelNodes);
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size();
        }

        @Override
        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();

            current = allNodes.get(nextIndex++);
            return current.value;
        }

        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException();

            Solution.this.remove(current.value);
        }

    }

    private static class Node {
        String value;
        Node prev;
        Node left;
        Node right;

        Node(String value) {
            this(value, null, null, null);
        }

        Node(String value, Node prev) {
            this(value, prev, null, null);
        }

        Node(String value, Node prev, Node left, Node right) {
            Node.this.value = value;
            Node.this.prev = prev;
            Node.this.left = left;
            Node.this.right = right;
        }
    }

    public void print() {
        System.out.println();
        System.out.println();
        System.out.println("====================");
        print(root, 0);
        System.out.println();
        System.out.println("====================");
        System.out.println();
    }

    public void print(Node node, int level) {
        if (level > 0) System.out.print("\t->\t"+node.value);

        if (node.left != null) print(node.left, level + 1);
        if (node.right != null) {
            System.out.println();
            for (int i = 0; i < level; i++) System.out.print("\t\t");
            print(node.right, level + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> list = new Solution();
        Node root = ((Solution)list).root;
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        ((Solution) list).print();
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
        ((Solution) list).print();




        List<String> list2 = new Solution();
        Node root2 = ((Solution) list2).root;
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
        ((Solution) list2).print();





        MyIterator iterator = (MyIterator)list2.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("3")) iterator.remove();
        }
        ((Solution) list2).print();





        Path tmpDir = Files.createTempDirectory("tmp_jr");
        Path tmpFile = Files.createTempFile(tmpDir, null, ".tmp_jr_file");
        tmpDir.toFile().deleteOnExit();
        tmpFile.toFile().deleteOnExit();
        try (
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tmpFile))
        ) {
            oos.writeObject(list2);
            oos.flush();
        }
        List<String> list3 = null;
        try (
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(tmpFile))
                ) {
            list3 = (List)in.readObject();
            ((Solution) list3).print();
        }
        Node root3 = ((Solution) list3).root;

    }
}
