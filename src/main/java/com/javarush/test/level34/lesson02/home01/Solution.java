package com.javarush.test.level34.lesson02.home01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = null;

        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);

//        s = "(-2)*(-2)";
//        System.out.print(s + " expected output 4 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "(-2)/(-2)";
//        System.out.print(s + " expected output 1 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "sin(-30)";
//        System.out.print(s + " expected output -0.5 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "cos(-30)";
//        System.out.print(s + " expected output 0.87 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "tan(-30)";
//        System.out.print(s + " expected output -0.58 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "cos(3 + 19*3)";
//        System.out.print(s + " expected output 0.5 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "2+8*(9/4-1.5)^(1+1)";
//        System.out.print(s + " expected output 6.5 6 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.005 ";
//        System.out.print(s + " expected output 0.01 0 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.0049 ";
//        System.out.print(s + " expected output 0 0 actually ");
//        solution.recursion(s, 0);
//
//        s = "tan(45)";
//        System.out.print(s + " expected output 1 1 actually ");
//        solution.recursion(s, 0);
//
//        s = "0+0.304";
//        System.out.print(s + " expected output 0.3 1 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.3051";
//        System.out.print(s + " expected output 0.31 0 actually ");
//        solution.recursion(s, 0);
//
//        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
//        System.out.print(s + " expected output 12 8 actually ");
//        solution.recursion(s, 0);
//
//        s = "tan(44+sin(89-cos(180)^2))";
//        System.out.print(s + " expected output 1 6 actually ");
//        solution.recursion(s, 0);
//
//        s = "-2+(-2+(-2)-2*(2+2))";
//        System.out.print(s + " expected output -14 8 actually ");
//        solution.recursion(s, 0);
//
//        s = "sin(80+(2+(1+1))*(1+1)+2)";
//        System.out.print(s + " expected output 1 7 actually ");
//        solution.recursion(s, 0);
//
//        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
//        System.out.print(s + " expected output 6 11 actually ");
//        solution.recursion(s, 0);
//
//        s = "2^10+2^(5+5)";
//        System.out.print(s + " expected output 2048 4 actually ");
//        solution.recursion(s, 0);
//
//        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
//        System.out.print(s + " expected output 72.96 8 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.000025+0.000012";
//        System.out.print(s + " expected output 0 1 actually ");
//        solution.recursion(s, 0);
//
//        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
//        System.out.print(s + " expected output -3 16 actually ");
//        solution.recursion(s, 0);
//
//        s = "cos(3 + 19*3)";
//        System.out.print(s + " expected output 0.5 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
//        System.out.print(s + " expected output 8302231.36 14 actually ");
//        solution.recursion(s, 0);
//
//        s = "(-1 + (-2))";
//        System.out.print(s + " expected output -3 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "-sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output -0.5 7 actually ");
//        solution.recursion(s, 0);
//
//        s = "sin(100)-sin(100)";
//        System.out.print(s + " expected output 0 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "-(-22+22*2)";
//        System.out.print(s + "expected output -22 4 actually ");
//        solution.recursion(s,0);
//
//        s = "-2^(-2)";
//        System.out.print(s + "expected output -0.25 3 actually ");
//        solution.recursion(s,0);
    }

    public void recursion(final String expression, int countOperation) {
        String texpression = expression;

        String simpleExpression = findFimpleExpression(texpression);
        int opsCount = countOperation + getOpsCount(simpleExpression);
        String calculated = calculate(simpleExpression);
        String rounded = getRounded(calculated);
        texpression = replaceSimpleExpression(texpression, simpleExpression, rounded);

        if (isSimple(texpression)) {
            System.out.println(rounded+" "+opsCount);
        } else {
            recursion(texpression, opsCount);
        }
    }

    private String replaceSimpleExpression(String expression, String simple, String rounded) {
        List<String> symbols = getSymbols(simple);
        String variant1 = "(" + symbols.get(0) + ")" + symbols.get(1) + symbols.get(2);
        String variant2 = symbols.get(0) + symbols.get(1) + "(" + symbols.get(2) + ")";

        if (expression.contains(simple)) expression = expression.replace(simple, rounded);
        else if (expression.contains(variant1)) expression = expression.replace(variant1, rounded);
        else if (expression.contains(variant2)) expression = expression.replace(variant2, rounded);

        return expression;
    }

    private int getOpsCount(String s) {
        List<String> rpn = getReversePolishNotation(s);
        int opsCount = 0;
        for (String str : rpn) {
            if (isOperator(str) || isFunc(str)) opsCount++;
        }
        return opsCount;
    }

    private String findFimpleExpression(String s) {
        List<String> rpn = getReversePolishNotation(s);
        String simple = "";
        for (int i = 0; i < rpn.size(); i++) {
            String symbol = rpn.get(i);
            if (isOperator(symbol)) {
                simple = rpn.get(i-2)+symbol+rpn.get(i-1);
                break;
            }
            if (isFunc(symbol)) {
                simple = symbol + "(" + rpn.get(i-1) + ")";
                break;
            }
        }
        return simple;
    }

    private boolean isSimple(String s) {

        if (s == null || s.isEmpty()) throw new IllegalStateException();
        String exp = s;
        if (exp.startsWith("-")) exp = exp.substring(1, exp.length());
//        if ((exp.length() > 3) && (isFunc(exp.substring(0, 3)))) exp = exp.substring(3, exp.length());
//        if ((exp.length() > 3) && (exp.startsWith("(") && exp.endsWith(")"))) exp = exp.substring(1, exp.length()-1);
        if (isOperand(exp)) return true;
        return false;
    }

    private String calculate(String s) {
        List<String> rpn = getReversePolishNotation(s);
        String result = calcRpn(rpn);
        if (s.startsWith("(") && s.endsWith(")")) result = "("+result+")";
        return result;
    }

    private String getRounded(String s) {
        double d = Double.parseDouble(s);
        double rounded = Math.round(d*100.0)/100.0;
        s = String.valueOf(rounded);
        if (s.endsWith(".00")) s = s.replace(".00", "");
        if (s.endsWith(".0")) s = s.replace(".0", "");
        return s;
    }

    String calcRpn(List<String> rpn) {
        Stack<String> stack = new Stack<>();

        while (!rpn.isEmpty()) {
            String s = rpn.remove(0);
            if (isOperator(s) && stack.size() > 1) {
                Double o2 = Double.parseDouble(stack.pop());
                Double o1 = Double.parseDouble(stack.pop());
                Double result;
                switch (s) {
                    case "+":
                        result = o1 + o2;
                        break;
                    case "-":
                        result = o1 - o2;
                        break;
                    case "*":
                        result = o1 * o2;
                        break;
                    case "/":
                        result = o1 / o2;
                        break;
                    case "^":
                        result = Math.pow(o1, o2);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                stack.push(Double.toString(result));
            }
            else if (isFunc(s) && stack.size() > 0) {

                Double angle = Double.parseDouble(stack.pop());
                Double radians = (angle * Math.PI) / 180;
                Double result;
                switch (s) {
                    case "sin":
                        result = Math.sin(radians);
                        break;
                    case "cos":
                        result = Math.cos(radians);
                        break;
                    case "tan":
                        result = Math.tan(radians);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                stack.push(Double.toString(result));
            }
            else {

                stack.push(s);
                continue;
            }
        }

        return stack.pop();
    }

    List<String> getReversePolishNotation(final String expression) {
        String texpression = expression;
        while (texpression.contains("(-")) texpression = texpression.replace("(-", "(0-");
        if (texpression.startsWith("-")) texpression = "0" + texpression;

        List<String> symbols = getSymbols(texpression);
        Stack<String> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        for (String symbol : symbols) {
            if (isOperand(symbol)) {
                output.add(symbol);
            }
            else if (symbol.equals("(")) {
                stack.push(symbol);
            }
            else if (isOperator(symbol) || isFunc(symbol)) {
                if (symbol.equals(")")) {
                    while(true) {
                        if (stack.isEmpty()) throw new IllegalStateException("Wrong formula: contains ) but doesn't contain (");

                        String s = stack.pop();
                        if (s.equals("(")) break;
                        output.add(s);
                    }
                } else {
                    if (!stack.isEmpty()) {
                        String topStack = stack.pop();
                        if (isLessOrEqPrioritized(symbol, topStack)) {
                            output.add(topStack);
                        } else {
                            stack.push(topStack);
                        }
                    }
                    stack.push(symbol);
                }
            }
            else throw new IllegalArgumentException("Illegal symbol : " + symbol);
        }

        while(stack.size() > 0) output.add(stack.pop());

        return output;
    }

    List<String> getSymbols(final String expression) {
        List<String> symbols = new ArrayList<>();
        char[] inputChars = expression.replaceAll("\\s", "").toCharArray();

        String current = "";
        for (int i = 0; i < inputChars.length; i++) {
            current += inputChars[i];

            if (!isOperand(current) && isOperand(current.substring(0, current.length()-1))) {
                symbols.add(current.substring(0, current.length()-1));
                current = current.substring(current.length()-1);
                if (current.isEmpty()) continue;
            }

            if (isOperator(current) || isFunc(current)) {
                symbols.add(current);
                current = "";
                continue;
            }
            else if (isOperand(current) || isPossibleFunc(current)) {
                continue;
            }

            throw new IllegalArgumentException("Incorrect symbols in formula, current: "+current+" , current char: "+inputChars[i]);
        }
        if (!current.isEmpty()) symbols.add(current);
        return symbols;
    }

    boolean isLessOrEqPrioritized(String symbol1, String symbol2) {
        int p1 = getOperatorPriotiry(symbol1);
        int p2 = getOperatorPriotiry(symbol2);
        return p1 <= p2;
    }

    int getOperatorPriotiry(String operator) {
        int priority = 5;
        switch (operator) {
            case "^":
                priority = 4;
                break;
            case "*":case "/":
                priority = 3;
                break;
            case "+":case "-":
                priority = 2;
                break;
            case "(":case ")":
                priority = 1;
                break;
        }
        return priority;
    }

    boolean isOperand(String s) {
        if (s.isEmpty()) return false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean isDigit = chars[i] >= 48 && chars[i] <= 57;
            boolean isPoint = chars[i] == '.';
            if (!isDigit && !isPoint) return false;
        }
        return true;
    }

    boolean isOperator(String s) {
        switch (s) {
            case "(": case ")": case "+": case "-": case "*": case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    boolean isFunc(String s) {
        switch (s) {
            case "sin": case "cos": case "tan":
                return true;
            default:
                return false;
        }
    }

    boolean isPossibleFunc(String s) {
        if (
                s.startsWith("s") ||
                s.startsWith("c") ||
                s.startsWith("t")
                ) return true;
        return false;
    }

    public Solution() {
        //don't delete
    }
}
