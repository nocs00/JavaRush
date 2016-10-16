package com.javarush.test.level34.lesson02.home01;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void recursion(final String expression, int countOperation) {
        String texpression = expression.replaceAll("\\s", "");
        int opsCount = countOperation != 0 ? countOperation : getOpsCount(expression);
        texpression = clean(texpression);

        String simpleExpression = findSimpleExpression(texpression);
        String calculated = calculateExpression(simpleExpression);
        String rounded = getRoundedExpression(calculated);
        texpression = replace(texpression, simpleExpression, rounded);

        if (isCalculatedExpression(texpression)) {
            rounded = rounded.substring(1, rounded.length()-1);
            System.out.println(rounded+" "+opsCount);
        } else {
            recursion(texpression, opsCount);
        }
    }

    private String clean(String expr) {
        int start = 0;
        int end;
        String match;
        String func;
        String operator;

        start = 0;
        while (expr.matches("^.*\\([0-9]+(\\.[0-9]+)?\\).*$")) { // (1) = 1
            start = expr.indexOf("(", start);
            if (start == -1) break;
            end = expr.indexOf(")", start)+1;
            match = expr.substring(start,end);
            func = start > 2 ? expr.substring(start-3, start) : "";

            if (match.matches("^\\([0-9]+(\\.[0-9]+)?\\)$")
                    && !isFunc(func)) {

                expr = expr.substring(0, start)
                        + match.replace("(", "").replace(")", "")
                        + expr.substring(end, expr.length());
            }
            else start++;
        }

        start = 0;
        while (expr.matches("^.*\\(\\-[0-9]+(\\.[0-9]+)?\\).*$")) { //(-1)  = -1
            start = expr.indexOf("(-", start);
            if (start == -1) break;
            end = expr.indexOf(")", start)+1;
            match = expr.substring(start,end);
            func = start > 2 ? expr.substring(start-3, start) : "";
            operator = start > 0 ? expr.substring(start-1, start) : "";
            boolean prioritizedOperator = isOperator(operator)
                    && (operator.equals("^") || operator.equals("*") || operator.equals("/") );

            if (match.matches("^\\(\\-[0-9]+(\\.[0-9]+)?\\)$")
                    && !isFunc(func)
                    && !prioritizedOperator) {

                expr = expr.substring(0, start)
                        + match.replace("(-", "-").replace(")", "")
                        + expr.substring(end, expr.length());
            }
            else start++;
        }

        expr = expr.replaceAll("\\-\\-", "+");
        expr = expr.replaceAll("\\+\\-", "-");
        expr = expr.replaceAll("\\-\\+", "-");

        return expr;
    }

    private String findSimpleExpression(String fullExpr) {

        if (isSimpleExpression(fullExpr)) return fullExpr;

        List<String> bracketExpressions = processBrackets(fullExpr);
        for (String expression : bracketExpressions) {
            if (isSimpleExpression(expression)) return expression;
        }

        List<String> functionExpressions = processFunctions(fullExpr);
        for (String expression : functionExpressions) {
            if (isSimpleExpression(expression)) return expression;
        }

        List<String> powExpressions = processPows(fullExpr);
        for (String expression : powExpressions) {
            if (isSimpleExpression(expression)) return expression;
        }

        List<String> mulDivExpressions = processMulDivs(fullExpr);
        for (String expression : mulDivExpressions) {
            if (isSimpleExpression(expression)) return expression;
        }

        List<String> plusMinusExpressions = processPlusMinus(fullExpr);
        for (String expression : plusMinusExpressions) {
            if (isSimpleExpression(expression)) return expression;
        }

        throw new IllegalArgumentException();
    }

    private List<String> processFunctions(String expr) {
        List<String> functions = Arrays.asList("sin", "cos", "tan");
        List<String> functionalExpressions = new ArrayList<>();
        for (String function : functions) {
            functionalExpressions.addAll(processFunctionalExpressions(expr, function));
        }
        return functionalExpressions;
    }

    private List<String> processPlusMinus(String expr) {
        List<String> result = processBinaryOperator(expr, "-");
        result.addAll(processBinaryOperator(expr, "+"));
        return result;
    }

    private List<String> processMulDivs(String expr) {
        List<String> result = processBinaryOperator(expr, "/");
        result.addAll(processBinaryOperator(expr, "*"));
        return result;
    }

    private List<String> processBinaryOperator(String expr, String operator) {
        List<String> symbols = getSymbols(expr);
        List<String> expressions = new ArrayList<>();

        String expression = "";
        String before = "";
        String after = "";
        int operatorIndex = -1;
        for (int i = 0; i < symbols.size(); i++) {
            String symbol = symbols.get(i);
            if (symbol.equals(operator)) {
                operatorIndex = i;
                if (isOperand(symbols.get(i-1))) {
                    expression += symbols.get(i-1);
                    if (i > 1) before = symbols.get(i-2);
                }
                else if (symbols.get(i-1).equals(")")) {
                    int closedBrackets = 0;
                    int index = i-1;
                    do {
                        expression = symbols.get(index) + expression;
                        if (symbols.get(index).equals("(")) closedBrackets--;
                        else if (symbols.get(index).equals(")")) closedBrackets++;
                    } while (!(symbols.get(index--).equals("(") && (closedBrackets == 0)));
                    if (index >= 0) before = symbols.get(index);
                }

                expression += operator;

                if (isOperand(symbols.get(i+1))) {
                    expression += symbols.get(i+1);
                    if ((i+2) < symbols.size()) after = symbols.get(i+2);

                    boolean isBeforeOperator = isOperator(before);
                    boolean isBeforeLessPriority = isLessOrEqPrioritized(before, operator);
                    boolean one = !isBeforeOperator || isBeforeLessPriority;

                    boolean isAfterOperator = isOperator(after);
                    boolean isAfterLessPriority = isLessOrEqPrioritized(after, operator);
                    boolean two = !isAfterOperator || isAfterLessPriority;

                    if (one && two) {
                        expressions.add(expression);
                    }

                    expression = "";
                    before = "";
                    after = "";
                    i++;
                }
                else if (symbols.get(i+1).equals("(")) {
                    int openBrackets = 0;
                    int index = i+1;
                    do {
                        expression += symbols.get(index);
                        if (symbols.get(index).equals("(")) openBrackets++;
                        else if (symbols.get(index).equals(")")) openBrackets--;
                    } while (!(symbols.get(index++).equals(")") && (openBrackets == 0)));

                    if (index < symbols.size()) after = symbols.get(index);

                    boolean isBeforeOperator = isOperator(before);
                    boolean isBeforeLessPriority = isLessOrEqPrioritized(before, operator);
                    boolean one = !isBeforeOperator || isBeforeLessPriority;

                    boolean isAfterOperator = isOperator(after);
                    boolean isAfterLessPriority = isLessOrEqPrioritized(after, operator);
                    boolean two = !isAfterOperator || isAfterLessPriority;

                    if (one && two) {
                        expressions.add(expression);
                    }

                    expression = "";
                    before = "";
                    after = "";
                    i = index;
                }
                i = operatorIndex + 1;
            }
        }

        return expressions;
    }

    private List<String> processPows(String expr) {
        return processBinaryOperator(expr, "^");
    }

    private boolean isSimpleExpression(String expression) {
        List<String> symbols = getSymbols(expression);
        if ((symbols.size() == 1) && isOperand(symbols.get(0))) return true;

        List<String> rpn = getReversePolishNotation(symbols);
        if (rpn.size() == 3) {
            if (isOperand(rpn.get(0))
                    && isOperand(rpn.get(1))
                    && isOperator(rpn.get(2))) return true;
        } else if (rpn.size() == 2) {
            if (isOperand(rpn.get(0))
                    && isFunc(rpn.get(1))) return true;
        }
        return false;
    }

    private List<String> getReversePolishNotation(List<String> symbols) {
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

    private boolean isLessOrEqPrioritized(String symbol1, String symbol2) {
        int p1 = getOperatorPriotiry(symbol1);
        int p2 = getOperatorPriotiry(symbol2);
        return p1 <= p2;
    }

    private int getOperatorPriotiry(String operator) {
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

    private List<String> processFunctionalExpressions(String expr, String function) {
        if (function == null) function = "";
        List<String> symbols = getSymbols(expr);
        List<String> bracketExpressions = new ArrayList<>();

        String expression = "";
//        boolean start = false;
        int bracketsOpen = 0;
//        boolean skip = false;

        for (int i = 0; i < symbols.size(); i++) {
            String symbol = symbols.get(i);

            if (symbol.equals("(")) {
                boolean functionBefore = function.isEmpty()
                        || ((i > 0) && symbols.get(i-1).equals(function));
                if (!functionBefore) continue;

                bracketsOpen = 1;
                expression = function+symbol;
                int index = i+1;
                while (bracketsOpen > 0) {
                    String s = symbols.get(index++);
                    expression += s;
                    if (s.equals(")")) bracketsOpen--;
                    else if (s.equals("(")) bracketsOpen++;
                }
                bracketExpressions.add(expression);
                expression = "";
            }
        }

        return bracketExpressions;
    }

    private List<String> processBrackets(String expr) {
        return processFunctionalExpressions(expr, null);
    }


    private List<String> getSymbols(String expression) {
        while (expression.contains("(-")) expression = expression.replace("(-", "(0-");
        if (expression.startsWith("-")) expression = "0" + expression;

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

        symbols = minimize(symbols);
        return symbols;
    }

    private List<String> minimize(List<String> symbols) {
        List<String> tsymbols = new ArrayList<>();
        tsymbols.add(symbols.get(0));

        for (int i = 1; i < symbols.size(); i++) {
            boolean inTheMiddle = (i >= 1) && (i < symbols.size()-1);
            boolean minus = symbols.get(i).equals("-");
            boolean zeroBehind = inTheMiddle && (symbols.get(i-1).endsWith("0"));
            if (inTheMiddle && minus && zeroBehind) {
                tsymbols.remove(tsymbols.size()-1);
                tsymbols.add("-" + symbols.get(i+1));
                i++;
            }
            else tsymbols.add(symbols.get(i));
        }

        if (!tsymbols.isEmpty() && tsymbols.get(0).equals("-(")) {
            tsymbols.remove(0);
            tsymbols.add(0,"(");
            tsymbols.add(0, "-");
        }
        return tsymbols;
    }

    private boolean isOperand(String s) {
        if (isOperator(s)) return false;
        if (s.isEmpty()) return false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == '-') continue;
            boolean isDigit = chars[i] >= 48 && chars[i] <= 57;
            boolean isPoint = chars[i] == '.';
            if (!isDigit && !isPoint) return false;
        }
        return true;
    }

    private boolean isOperator(String s) {
        switch (s) {
            case "(": case ")": case "+": case "-": case "*": case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    private boolean isFunc(String s) {
        if (s.startsWith("-")) s = s.substring(1);
        switch (s) {
            case "sin": case "cos": case "tan":
                return true;
            default:
                return false;
        }
    }

    private boolean isPossibleFunc(String s) {
        if (
                s.startsWith("s") ||
                        s.startsWith("c") ||
                        s.startsWith("t")
                ) return true;
        return false;
    }

    private int getOpsCount(String expression) {
        return
                expression.split("\\-").length-1
                + expression.split("\\+").length-1
                + expression.split("\\*").length-1
                + expression.split("\\/").length-1
                + expression.split("\\^").length-1
                + expression.split("sin").length-1
                + expression.split("cos").length-1
                + expression.split("tan").length-1;
    }

    private String calculateExpression(String simpleExpr) {
        List<String> rpn = getReversePolishNotation(getSymbols(simpleExpr));
        String result = calcRpn(rpn);
//        if (simpleExpr.startsWith("(") && simpleExpr.endsWith(")")) result = "("+result+")";
        return result;
    }

    private String calcRpn(List<String> rpn) {
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
                boolean minus = s.startsWith("-");
                if (minus) s = s.substring(1);
                Double angle = Double.parseDouble(stack.pop());
                Double radians = Math.toRadians(angle);
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
                stack.push(Double.toString(minus ? -result : result));
            }
            else {

                stack.push(s);
                continue;
            }
        }

        return stack.pop();
    }

    private String getRoundedExpression(String calculatedExpr) {
        if (calculatedExpr.contains("E")) {
            String exponent = calculatedExpr.substring(calculatedExpr.indexOf("E"), calculatedExpr.length());
            String operator = exponent.substring(1,2);
            int num = Integer.parseInt(exponent.replace("E", "").replace(operator, ""));
            calculatedExpr = calculatedExpr.replace(exponent, "");
            double dd = Double.parseDouble(calculatedExpr);
            for (int i = 0; i < num; i++)
                dd = operator.equals("-") ? dd / 10.0 : dd * 10.0;
            calculatedExpr = String.valueOf(dd);
        }

        double d = Double.parseDouble(calculatedExpr);
        double rounded = Math.round(d*100.0)/100.0;
        calculatedExpr = String.valueOf(rounded);
        if (calculatedExpr.endsWith(".00")) calculatedExpr = calculatedExpr.replace(".00", "");
        if (calculatedExpr.endsWith(".0")) calculatedExpr = calculatedExpr.replace(".0", "");
        return "(" + calculatedExpr + ")";
    }

    private String replace(String where, String what, String with) {
        return where.replace(what, with);
    }

    private boolean isCalculatedExpression(String s) {
        if (s == null || s.isEmpty()) throw new IllegalStateException();
        List<String> rpn = getReversePolishNotation(getSymbols(s));
        if ((rpn.size() == 1) && isOperand(rpn.get(0))) return true;
//        String exp = s;
//        if (exp.startsWith("-")) exp = exp.substring(1, exp.length());
//        if (isOperand(exp)) return true;
        return false;
    }

    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = null;

//        s = "sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output 0.5 6 actually ");
//        solution.recursion(s, 0);
//
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

        s = "-2^(-2)";
        System.out.print(s + "expected output -0.25 3 actually ");
        solution.recursion(s,0);
    }
}
