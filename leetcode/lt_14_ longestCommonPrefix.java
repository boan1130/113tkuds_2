class Solution {
    // 題目：Longest Common Prefix
    // 找出字串陣列中最長的共同前綴
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始逐一比對
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到當前字串包含 prefix 為止
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 假設第一個字串是共同前綴，逐一和其他字串比較。
2. 若當前字串不以 prefix 開頭，就不斷縮短 prefix。
3. 若縮短到空字串，代表沒有共同前綴。
4. 完成所有字串的比對後，剩下的 prefix 就是最長共同前綴。

例子：
- ["flower","flow","flight"] → "fl"
- ["dog","racecar","car"]   → ""

時間複雜度：O(n * m)，其中 n = 字串數量，m = 最長字串長度。
空間複雜度：O(1)，只用到變數 prefix。
*/
