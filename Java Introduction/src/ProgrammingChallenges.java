/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.Objects;

/**
 * Various Programming Challenges
 *
 * @author Clayton Hammen Tan
 * @since 04-03-24
 */

public class ProgrammingChallenges {
    private static final int DIV_BY_7 = 7;
    private static final int TWO_DP = 100;
    private static final int SINGLE_DIGIT = 10;
    private static final int THREE_DP_I = 1000;
    private static final float THREE_DP_F = 1000f;
    /**
     * Problem 1
     * Boolean Operations
     * @param item the food item
     * @param inWallet total amount of money
     * @param needed amount of money needed
     * @return Boolean on whether item is correct and there is enough money.
     */
    public static boolean store(String item, float inWallet, float needed) {
        boolean correctItem = item.equals("cake") || item.equals("ice-cream")
                || item.equals("sushi");

        boolean budget = inWallet >= needed;

        return correctItem && budget;

    }

    /**
     * Problem 2
     * Array Manipulations
     * @param arr1 the first integer array
     * @param arr2 the second integer array
     * @return Boolean on whether all three conditions are met.
     */
    public static boolean compareArrays(int[] arr1, int[] arr2) {
       boolean lengthCond = arr1.length == (arr2.length * 2);

       boolean firstCond = arr1[0] == -1*(arr2[arr2.length-1]);

       boolean lastCond = arr1[arr1.length-1] == -1*(arr2[0]);

       return lengthCond && firstCond && lastCond;
    }

    /**
     * Problem 3
     * Single For loop and array practice
     * @param arr1 array of integers
     * @return Integer determining the amount of odd numbers not divisible by 7.
     */
    public static int countNumbers(int[] arr1) {
        int count = 0;
        for(int num : arr1) {
            if(num % 2 != 0 && num % DIV_BY_7 != 0) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Problem 4
     * Single While loop
     * @param arr1 integer array
     * @return The number of positive integers and their average in an array.
     */
    public static float[] positiveAverage(int[] arr1) {
        int count = 0;
        float sum = 0f;
        int i = 0;

        while(i < arr1.length) {
            if(arr1[i] > 0) {
                count += 1;
                sum += arr1[i];
            }
            i += 1;
        }
        float average;
        if(count == 0) {
            average = 0;
        }
        else {
            average = (float) Math.round((sum/count)*TWO_DP) / TWO_DP;
        }
        return new float[]{count,average};
    }

    /**
     * Problem 5
     * Combining While loop and Boolean
     * @param num1 first integer
     * @param num2 second integer
     * @return Boolean on whether the first digit of the first number is equal to the
     * last digit of the second number.
     */
    public static boolean sameDigitFirstAndLast(int num1, int num2) {
        while(num1 >= SINGLE_DIGIT) {
            num1 /= SINGLE_DIGIT;
        }
        num2 %= SINGLE_DIGIT;
        return num1 == num2;
    }

    /**
     * Problem 6
     * Combining For loop and Boolean
     * @param elems integer array
     * @return Boolean whether there is three consecutive decreasing numbers.
     */
    public static boolean decreasingOrder(int[] elems) {
        for(int i = 0; i < elems.length - 2; i++) {
            if(elems[i] > elems[i+1] && elems[i+1] > elems[i+2]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Problem 7
     * Manipulating a 2-D array
     * @param elems 2D integer array
     */
    public static void replaceMainDiagonal(int[][] elems) {
        int sum = 0;
        for(int i = 0; i < elems.length; i++) {
            sum += elems[i][i];
        }
        for(int i = 0; i < elems.length; i++) {
            elems[i][i] = sum;
        }
    }

    /**
     * Problem 8
     * Loop and Array
     * @param grades 2D integer array
     * @return An array of the average of each sub array.
     */
    public static float[] assignmentAverages (int[][] grades) {
        float[] averages = new float[grades[0].length];
        for(int i = 0; i < grades[0].length; i++) {
            float sum = 0;
            for(int j = 0; j < grades.length; j++) {
                sum += grades[j][i];
            }
            averages[i] = Math.round(sum / grades.length * THREE_DP_I) / THREE_DP_F;
        }
        return averages;
    }

    /**
     * Problem 9
     * Recursion and String Operations
     * @param str A string
     * @return A new string with all "." removed.
     */
    public static String noDots(String str) {
        if(str.isEmpty()) {
            return str;
        }
        if(str.charAt(0) == '.') {
            return noDots(str.substring(1));
        }
        else {
            return str.charAt(0) + noDots((str.substring(1)));
        }
    }

    /**
     * Problem 10
     * Conditionals and Arrays
     * @param elems integer array
     * @return An integer array based on the conditions.
     */
    public static int[] twoElements(int[] elems) {
        if(elems.length < 2) {
            return new int[0];
        }
        int[] output = new int[2];
        if(elems.length % 2 == 0) {
            output[0] = elems[(elems.length/2) - 1];
            output[1] = elems[elems.length/2];
        }
        else {
            output[0] = elems.length;
            output[1] = Math.min(elems[0], elems[elems.length-1]);
        }
        return output;
    }

}
