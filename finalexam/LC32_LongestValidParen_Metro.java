package finalexam;

import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(longestValidParentheses(s));
    }

    /**
     * 使用索引棧方法
     * 時間 O(n)，空間 O(n)
     */
    private static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 初始基準
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else { // c == ')'
                if (!stack.isEmpty()) stack.pop();

                if (stack.isEmpty()) {
                    // 沒有可配對的 '('，重置基準
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
