class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        int start = 0, end = 0; // 最長回文的左右邊界
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // 奇數長度中心
            int len2 = expand(s, i, i + 1);   // 偶數長度中心
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end   = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 以 left、right 為中心向外擴展，回傳最長回文長度
    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 超出一格後的長度
    }
}
