import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression);
        
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        
        return ans;
    }

    private Set<String> solve(String s) {
        Set<String> result = new HashSet<>();

        int start = -1;
        int end = -1;
        int count = 0;

        // Find the first complete {...}
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                if (count == 0) {
                    start = i;
                }
                count++;
            } else if (s.charAt(i) == '}') {
                count--;

                if (count == 0) {
                    end = i;
                    break;
                }
            }
        }

        // No braces
        if (start == -1) {
            result.add(s);
            return result;
        }

        // Part before and after braces
        String before = s.substring(0, start);
        String inside = s.substring(start + 1, end);
        String after = s.substring(end + 1);

        // Split inside by commas
        List<String> parts = split(inside);

        // Expand each choice
        for (String part : parts) {
            Set<String> expanded = solve(part);

            for (String x : expanded) {
                result.addAll(combine(before + x, after));
            }
        }

        return result;
    }

    private List<String> split(String s) {
        List<String> parts = new ArrayList<>();

        int count = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                count++;
            } else if (s.charAt(i) == '}') {
                count--;
            } else if (s.charAt(i) == ',' && count == 0) {
                parts.add(s.substring(start, i));
                start = i + 1;
            }
        }

        parts.add(s.substring(start));

        return parts;
    }

    private Set<String> combine(String left, String right) {
        Set<String> result = new HashSet<>();

        Set<String> leftSet = solve(left);
        Set<String> rightSet = solve(right);

        for (String a : leftSet) {
            for (String b : rightSet) {
                result.add(a + b);
            }
        }

        return result;
    }
}