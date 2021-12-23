package com.javarush.task.task20.task2025;

import java.math.BigDecimal;
import java.util.*;

/* 
Алгоритмы-числа
*/

public class Solution {
    public static long[][] table = new long[10][20];

    static {
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 20; j++) {
                if (j == 0 && i != 0) {
                    table[i][j] = 1;
                    continue;
                }
                table[i][j] = (long) i;
                for (int k = 1; k < j; k++) {
                    table[i][j] = table[i][j] * i;
                }
            }
        }
    }

    public static long sum(long[] array, int len) {

        long sum = 0L;
        for (int i = 0; i < array.length; i++) {
            long digit = array[i];
            sum = sum + table[(int) digit][len];

        }
        return sum;
    }

    public static boolean isArmstrong(long[] array, int length) {
        long sum = sum(array, length);
        if (sum < 0) {
            return false;
        }
        String sumStr = String.valueOf(sum);
        long sum1 = sum(sumStr);
        return sum1 == sum;
    }

    public static long sum(String number) {

        int length = number.length();
        long sum = 0L;
        for (int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            sum = sum + table[digit][length];

        }
        return sum;
    }

    public static long[] getNumbers(long N) {
        TreeSet<Long> resultSet = new TreeSet<>();

        if (N < 2) {
            return new long[0];
        }
        String number = String.valueOf(N);
        long[] array = new long[number.length()];
        Arrays.fill(array, 9L);

        for (int i = 0; i < array.length; i++) {

            while (array[i] > 0) {
                if (i == 0) {
                    array[i]--;
                   // Arrays.fill(array, 0, i, array[i]);
                    if (isArmstrong(array, array.length)) {
                        if (sum(array, array.length) < N) {
                            resultSet.add(sum(array, array.length));

                        }
                    }
                    int len = array.length;
                    for (int k = 0; k < array.length; k++) {
                        if (array[k] == 0) {
                            len--;
                            if (isArmstrong(array, len)) {
                                if (sum(array, len) < N) {
                                    resultSet.add(sum(array, len));

                                }
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    if (array[i - 1] == 0) {
                        array[i]--;
                        Arrays.fill(array, 0, i, array[i]);

                        if (isArmstrong(array, array.length)) {
                            if (sum(array, array.length) < N) {
                                resultSet.add(sum(array, array.length));

                            }
                        }
                        int len = array.length;
                        for (int k = 0; k < array.length; k++) {
                            if (array[k] == 0) {
                                len--;
                                if (isArmstrong(array, len)) {
                                    if (sum(array, len) < N) {
                                        resultSet.add(sum(array, len));

                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    } else {

                        i = 0;
                    }
                }
            }
        }
        if (resultSet.contains(0L)) {
            resultSet.remove(0L);
        }
        Long[] result = new Long[resultSet.size()];
        result = resultSet.toArray(result);
        long[] result1 = new long[result.length];
        for (int i = 0; i < result.length; i++) {
            result1[i] = result[i];
        }
        resultSet.clear();
        return result1;
    }

    public static void main(String[] args) {

        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);


    }
}
