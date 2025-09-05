package finalexam;

import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(isValid(s));
    }

    /**
     * 驗證括號是否合法
     * 使用 Stack + Map (閉括號對應開括號)
     * 時間 O(n)，空間 O(n)
     */
    private static boolean isValid(String s) {
        if (s == null) return false;
        if (s.length() == 0) return true; // 空字串合法

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                // 碰到閉括號，檢查棧頂
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                // 開括號 push
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
