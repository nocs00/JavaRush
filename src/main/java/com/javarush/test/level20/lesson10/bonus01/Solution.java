package com.javarush.test.level20.lesson10.bonus01;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int size = 10;
        int[] result = new int[size];
        int lastIndex = 0;

        for (int i = 1; i <= N; i++) {
            if (isWantedNumber(i)) {
                result[lastIndex++] = i;
                if (lastIndex >= result.length) {
                    size *= 2;
                    result = resize(size, result, result.length);
                }
            }
        }

        if (result.length > lastIndex) {
            result = resize(lastIndex, result, lastIndex);
        }

        return result;
    }

    private static int[] resize(int newSize, int[] oldArray, int length) {
        int[] newArray = new int[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, length);
        return newArray;
    }

    private static boolean isWantedNumber(int N) {
        int result = 0;
        int power = 0;
        int tmp = N;
        while (tmp > 0) {
            tmp = tmp / 10;
            power++;
        }
        tmp = N;
        while (tmp > 0) {
            int current = tmp % 10;
            tmp = tmp / 10;
            int powered = current;
            for (int i = 0; i < power-1; i++) powered *= current;
            result += powered;
            if (result > N) return false;
        }

        if (N == result) return true;
        return false;
    }

    private static void print(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (int n : numbers) {
            if (first) first = false;
            else sb.append(", ");
            sb.append(n);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
//        int[] numbers = getNumbers(Integer.MAX_VALUE);
//        print(numbers);
//        isWantedNumber(370);


        long memoryStart = Runtime.getRuntime().freeMemory();
        Long t0 = System.currentTimeMillis();
        int[]result = getNumbers(100000000);
        long memoryEnd = Runtime.getRuntime().maxMemory();
        long memoTaken = memoryStart - memoryEnd;
        System.out.println(memoTaken);
        Long t1 = System.currentTimeMillis();
        System.out.println("Time need to create the arrray = " + (t1 - t0));
        System.out.println("Used Memory in JVM: " + (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()));
    }
}
