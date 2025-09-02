import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> last = new HashMap<>(); // 字元 -> 最後索引
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c)) {
                // 將左邊界跳到「該字元上次出現的下一個位置」與目前 left 的最大值
                left = Math.max(left, last.get(c) + 1);
            }
            last.put(c, right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}