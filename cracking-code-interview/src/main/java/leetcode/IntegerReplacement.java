package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class IntegerReplacement {
    public static void main(String[] args) {
        System.out.println(new IntegerReplacement().integerReplacement(Integer.MAX_VALUE));
    }

    HashMap<Integer, Integer> calculatedSteps = new HashMap<>();

    public int integerReplacement(int n) {
        return integerReplacement(n, 0);
    }

    public int integerReplacement(int n, int step) {
        if (calculatedSteps.containsKey(n)) return calculatedSteps.get(n) + step;

        while (n % 2 == 0) {
            n /= 2;
            ++step;
        }

        if (n <= 1) {
            return step;
        }

        int minusSteps;
        if (calculatedSteps.containsKey(n - 1)) {
            minusSteps = calculatedSteps.get(n - 1);
        } else {
            minusSteps = integerReplacement(n - 1, step + 1);
        }

        int plusSteps;
        if (calculatedSteps.containsKey(n + 1)) {
            plusSteps = calculatedSteps.get(n + 1);
        } else {
            plusSteps = integerReplacement(n + 1, step + 1);
        }

        int steps = Math.min(minusSteps, plusSteps);
        calculatedSteps.put(n, steps);
        return steps;
    }

    ArrayList<Integer> results = new ArrayList<>();

    public int integerReplacementBFRec(int n) {
        integerReplacementBFRec(n, 0);
        return results.stream().min(Comparator.naturalOrder()).orElseThrow();
    }

    public void integerReplacementBFRec(int n, int step) {
        while (n % 2 == 0) {
            n /= 2;
            ++step;
        }

        if (n == 1) {
            results.add(step);
            return;
        }

        integerReplacementBFRec(n - 1, step + 1);
        integerReplacementBFRec(n + 1, step + 1);
    }
}
