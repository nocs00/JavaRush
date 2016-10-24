package com.javarush.test.level36.lesson08.bonus01;

/* Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберитесь в коде и исправьте ошибки.
Метод main не участвует в тестировании.
Все модификатры правильные.
Имена переменных и методов не изменяйте.
*/
public class Solution {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        System.out.println(tree.isEmpty());

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);

        System.out.println(tree.isEmpty());

    }
}
