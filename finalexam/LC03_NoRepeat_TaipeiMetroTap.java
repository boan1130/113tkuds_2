package finalexam;

import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 找最長不含重複字元的子字串長度
     * 使用滑動視窗 + HashMap<char, lastIndex>
     * 時間複雜度 O(n)，空間 O(k)，其中 k 為字元集大小
     */
    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0; // 視窗左邊界

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 若發現重複字元，更新左界（取最大確保不往左退）
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, right); // 記錄當前字元最後一次出現的位置
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
