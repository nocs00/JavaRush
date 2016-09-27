package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

import java.math.BigInteger;

public class Solution {
    public Solution(int m) {

    }
    public Solution(char m) {

    }
    public Solution(String m) {

    }

    protected Solution(short m) {

    }
    protected Solution(float m) {

    }
    protected Solution(double m) {

    }

    private Solution(Double m) {

    }
    private Solution(Float m) {

    }
    private Solution(Short m) {

    }

    Solution (Object m) {

    }
    Solution (Integer m) {

    }
    Solution (BigInteger m) {

    }
}

