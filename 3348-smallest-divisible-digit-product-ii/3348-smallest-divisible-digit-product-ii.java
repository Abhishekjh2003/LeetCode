import java.util.*;

class Solution {
    private static final Map<Integer, Map<Integer, Integer>> FACTOR_COUNTS = new HashMap<>();
    static {
        FACTOR_COUNTS.put(0, Map.of());
        FACTOR_COUNTS.put(1, Map.of());
        FACTOR_COUNTS.put(2, Map.of(2, 1));
        FACTOR_COUNTS.put(3, Map.of(3, 1));
        FACTOR_COUNTS.put(4, Map.of(2, 2));
        FACTOR_COUNTS.put(5, Map.of(5, 1));
        FACTOR_COUNTS.put(6, Map.of(2, 1, 3, 1));
        FACTOR_COUNTS.put(7, Map.of(7, 1));
        FACTOR_COUNTS.put(8, Map.of(2, 3));
        FACTOR_COUNTS.put(9, Map.of(3, 2));
    }

    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t (only prime factors 2, 3, 5, 7 are allowed)
        Pair primeRes = getPrimeCount(t);
        if (!primeRes.isDivisible) {
            return "-1";
        }
        Map<Integer, Integer> primeCount = primeRes.count;

        // Check if t requires more digits than len(num)
        Map<Integer, Integer> factorCount = getFactorCount(primeCount);
        if (sumValues(factorCount) > num.length()) {
            return construct(factorCount);
        }

        // Calculate prime factors present in the full original number
        Map<Integer, Integer> primeCountPrefix = getPrimeCount(num);
        int firstZeroIndex = num.indexOf('0');
        if (firstZeroIndex == -1) {
            firstZeroIndex = num.length();
            if (isSubset(primeCount, primeCountPrefix)) {
                return num; // Original num is valid and divisible
            }
        }

        // Step 2: Backtrack from right to left to keep maximum matching prefix
        for (int i = num.length() - 1; i >= 0; --i) {
            int d = num.charAt(i) - '0';
            primeCountPrefix = subtract(primeCountPrefix, FACTOR_COUNTS.get(d));
            int spaceAfter = num.length() - 1 - i;

            if (i <= firstZeroIndex) {
                for (int biggerDigit = d + 1; biggerDigit < 10; ++biggerDigit) {
                    // Prime factors required after placing biggerDigit at index i
                    Map<Integer, Integer> remPrime = subtract(
                        subtract(primeCount, primeCountPrefix),
                        FACTOR_COUNTS.get(biggerDigit)
                    );
                    Map<Integer, Integer> reqFactors = getFactorCount(remPrime);

                    // Check if remaining factor digits fit in space after position i
                    if (sumValues(reqFactors) <= spaceAfter) {
                        int fillOnes = spaceAfter - sumValues(reqFactors);
                        return num.substring(0, i) 
                                + biggerDigit 
                                + "1".repeat(fillOnes) 
                                + construct(reqFactors);
                    }
                }
            }
        }

        // Step 3: Extend length by 1 if no valid string of same length exists
        Map<Integer, Integer> extFactors = getFactorCount(primeCount);
        int fillOnes = num.length() + 1 - sumValues(extFactors);
        return "1".repeat(fillOnes) + construct(extFactors);
    }

    private static class Pair {
        Map<Integer, Integer> count;
        boolean isDivisible;
        Pair(Map<Integer, Integer> count, boolean isDivisible) {
            this.count = count;
            this.isDivisible = isDivisible;
        }
    }

    private Pair getPrimeCount(long t) {
        Map<Integer, Integer> count = new HashMap<>(Map.of(2, 0, 3, 0, 5, 0, 7, 0));
        int[] primes = {2, 3, 5, 7};
        for (int prime : primes) {
            while (t % prime == 0) {
                t /= prime;
                count.put(prime, count.get(prime) + 1);
            }
        }
        return new Pair(count, t == 1);
    }

    private Map<Integer, Integer> getPrimeCount(String num) {
        Map<Integer, Integer> count = new HashMap<>(Map.of(2, 0, 3, 0, 5, 0, 7, 0));
        for (char c : num.toCharArray()) {
            Map<Integer, Integer> digitFactors = FACTOR_COUNTS.get(c - '0');
            for (Map.Entry<Integer, Integer> entry : digitFactors.entrySet()) {
                count.put(entry.getKey(), count.get(entry.getKey()) + entry.getValue());
            }
        }
        return count;
    }

    private Map<Integer, Integer> getFactorCount(Map<Integer, Integer> count) {
        int c2 = Math.max(0, count.getOrDefault(2, 0));
        int c3 = Math.max(0, count.getOrDefault(3, 0));
        int c5 = Math.max(0, count.getOrDefault(5, 0));
        int c7 = Math.max(0, count.getOrDefault(7, 0));

        int count8 = c2 / 3, rem2 = c2 % 3;
        int count9 = c3 / 2, rem3 = c3 % 2;
        int count4 = rem2 / 2, count2 = rem2 % 2;
        int count3 = rem3, count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0; count3 = 0; count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1; count6 = 1; count3 = 0; count4 = 0;
        }

        Map<Integer, Integer> res = new HashMap<>();
        res.put(2, count2); res.put(3, count3); res.put(4, count4); res.put(5, c5);
        res.put(6, count6); res.put(7, c7); res.put(8, count8); res.put(9, count9);
        return res;
    }

    private String construct(Map<Integer, Integer> factors) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 2; digit < 10; ++digit) {
            int freq = factors.getOrDefault(digit, 0);
            if (freq > 0) {
                sb.append(String.valueOf(digit).repeat(freq));
            }
        }
        return sb.toString();
    }

    private boolean isSubset(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        for (Map.Entry<Integer, Integer> entry : a.entrySet()) {
            if (b.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Integer> subtract(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        Map<Integer, Integer> res = new HashMap<>(a);
        for (Map.Entry<Integer, Integer> entry : b.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            res.put(key, Math.max(0, res.getOrDefault(key, 0) - val));
        }
        return res;
    }

    private int sumValues(Map<Integer, Integer> count) {
        int sum = 0;
        for (int v : count.values()) {
            sum += v;
        }
        return sum;
    }
}