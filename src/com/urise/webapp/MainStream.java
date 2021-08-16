package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 3, 3};
        System.out.println(minValue(array));

        ArrayList<Integer> testList = new ArrayList<>(Arrays.asList(1, 2, 3, 1));
        System.out.println(oddOrEven(testList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> 10 * x + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int mod = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        return integers.stream().filter(n -> n % 2 != mod).collect(Collectors.toList());
    }
}