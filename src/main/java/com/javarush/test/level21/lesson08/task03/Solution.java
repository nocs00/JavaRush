package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Разрешите клонировать класс А
Запретите клонировать класс B
Разрешите клонировать класс C
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws Exception
    {

        A a_a = new A(1,2);
        A a_b = new B(1,2,null);
        A a_c = new C(1,2, null);

        B b = new B(1,2, null);
        B b_c = new C(1,2, null);

        C c =  new C(1,2,null);


        A a1, a2, a3;
        B b1, b2;
        C c1;

        a1 = a_a.clone();
        //a2 = a_b.clone();
        a3 = a_c.clone();

        //b1 = b.clone();
        b2 = b_c.clone();

        c1 = c.clone();
    }

    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected A clone() throws CloneNotSupportedException
        {
            final A a = new A(this.i, this.j);
            return a;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected B clone() throws CloneNotSupportedException
        {
            if (1 == 1)
                throw new CloneNotSupportedException();
            return null;
        }
    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone() throws CloneNotSupportedException
        {
            final C c = new C(this.getI(), this.getJ(), this.getName()==null?null:new String(this.getName()));
            return c;
        }
    }
}
