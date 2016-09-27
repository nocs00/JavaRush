package com.javarush.test.level20.lesson10.bonus01;

import java.util.*;

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
    public static long[] getNumbers(long N) {
        HashSet<int[]> checked = new HashSet<int[]>();
        HashSet<Long> resultSet = new HashSet<Long>();
        long[] result = null;

        for (long i = 1; i <= N; i++) {
//            if (i == 12345)
//                i += 0;
//
            int[] digits = getDigits(i);
            Arrays.sort(digits);

            if (checked.contains(digits)) {
                continue;
            }


            int M = getNumDigits(i);
            int total = 0;
            for (int c : digits) {
                if (total > i) break;
                else if (c == 0) continue;
                else if (c == 1) total += 1;
                else total += Math.pow(c, M);
            }
            if (total == i) {
                resultSet.add(i);
                checked.add(digits);
            }
        }

        result = new long[resultSet.size()];
        Iterator<Long> iterator = resultSet.iterator();
        for (int i = 0; i < resultSet.size(); i++) {
            result[i] = iterator.next();
        }
        Arrays.sort(result);
        return result;
    }

    public static int[] getDigits(long N) {
        int [] digits = new int[20];
        int i = 0;
        do
        {
            int digit = (int) (N - (N / 10) * 10);
            digits[i++] = digit;
        } while ((N /= 10) > 0) ;

        int[] result = new int[i];
        System.arraycopy(digits, 0, result, 0, i);
        digits = null;

        for(int k = 0; k < result.length / 2; k++)
        {
            int temp = result[k];
            result[k] = result[result.length - k - 1];
            result[result.length - k - 1] = temp;
        }

        return result;
    }

    public static int getNumDigits(long N) {
        int count = 0;

        do count++;
        while ((N /= 10) > 0) ;

        return count;
    }


    public static void main(String[] args)
    {
        long memoryStart = Runtime.getRuntime().freeMemory();
        Date start = new Date();
        long[] test = getNumbers(10000000);
        Date stop = new Date();
        long memoryEnd = Runtime.getRuntime().freeMemory();
        long memoTaken = memoryEnd - memoryStart;

        System.out.println("time taken in Sec: " +((double)(stop.getTime()-start.getTime()))/1000d);
        System.out.println("memory taken in MB: "+memoTaken/(1024*1024));
    }
}
