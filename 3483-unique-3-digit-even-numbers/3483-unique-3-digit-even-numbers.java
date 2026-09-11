import java.util.*;

class Solution {
    public int totalNumbers(int[] digits) {
        
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {

                    // Same digit position cannot be used twice
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    // First digit cannot be 0
                    if (digits[i] == 0) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number = digits[i] * 100
                              + digits[j] * 10
                              + digits[k];

                    set.add(number);
                }
            }
        }

        return set.size();
    }
}