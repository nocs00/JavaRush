package com.javarush.test.level34.lesson02.home01;

import java.util.Arrays;
import java.util.List;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution {
    public final static List<String> OPERATIONS = Arrays.asList("sin", "cos", "tan", "*", "-", "+", "^");
    public final static String PLUS_OPERATOR = "+";
    public final static String MINUS_OPERATOR = "-";
    public final static String MUL_OPERATOR = "*";
    public final static String POW_OPERATOR = "^";
    public final static String SINUS_OPERATOR = "sin";
    public final static String COSINUS_OPERATOR = "cos";
    public final static String TANGENS_OPERATOR = "tan";

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        solution.recursion("2+(3+(4+7))", 0);
    }

    public void recursion(final String expression, int countOperation) {
        //implement
        String nestedExpression = findBrackets(expression);
        if (nestedExpression == null) {
            return;
        } else {
            recursion(nestedExpression, 0);
        }
        //here we calc result
        countOperation++;
        calculateAtomicExpression(nestedExpression);
        return ;
    }

    private String findOperator(String expression) {
        for (String op : OPERATIONS) {
            if (expression.contains(op)) {
                return op;
            }
        }
        return null;
    }

//    private List<Float> findOperands(String expression, String operator) {
//
//    }

    private void calculateAtomicExpression(String atomicExpression) {
        String operator = findOperator(atomicExpression);
        switch (operator) {
            case PLUS_OPERATOR:

                break;
        }
    }

    private String findBrackets(String expression) {
        int openBracketIndex = expression.indexOf("(");
        int closeBracketIndex = -1;
        if (openBracketIndex != -1) {
            closeBracketIndex = expression.lastIndexOf(")");
            if (closeBracketIndex != -1) {
                return expression.substring(openBracketIndex+1, closeBracketIndex);
            }
        }
        return null;
    }

    public Solution() {
        //don't delete
    }
}
