// 題目：Count and Say
// 已知 countAndSay(1) = "1"
// 定義 countAndSay(n) = countAndSay(n-1) 的 RLE（逐位「數字+次數」描述）。
// 請回傳 countAndSay(n)。

class Solution {
    public String countAndSay(int n) {
        String result = "1"; // base case
        for (int i = 2; i <= n; i++) {
            result = nextSequence(result); // 每次生成下一項
        }
        return result;
    }

    // 幫助函式：對一個字串做 RLE
    private String nextSequence(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            // 檢查是否連續
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                // 當前 run 結束，輸出 count + 字元
                sb.append(count).append(s.charAt(i - 1));
                count = 1; // 重置
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 題目定義 countAndSay(1) = "1"。
2. 從 2 開始，逐步根據前一項進行 Run-Length Encoding (RLE)：
   - 遍歷字串，計算連續相同字元的長度 count。
   - 當 run 結束時，輸出「count + 字元」。
3. 疊代 n-1 次即可得到答案。
4. 範例：
   n=1 → "1"
   n=2 → "11"  (一個 1)
   n=3 → "21"  (兩個 1)
   n=4 → "1211" (一個 2，一個 1)
5. 複雜度：
   - 時間：O(n * m)，m 為字串平均長度，隨 n 增長而增大，但 n ≤ 30 時可接受。
   - 空間：O(m)，用於生成新字串。
*/
// 題目：Count and Say
// 已知 countAndSay(1) = "1"
// 定義 countAndSay(n) = countAndSay(n-1) 的 RLE（逐位「數字+次數」描述）。
// 請回傳 countAndSay(n)。

class Solution {
    public String countAndSay(int n) {
        String result = "1"; // base case
        for (int i = 2; i <= n; i++) {
            result = nextSequence(result); // 每次生成下一項
        }
        return result;
    }

    // 幫助函式：對一個字串做 RLE
    private String nextSequence(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            // 檢查是否連續
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                // 當前 run 結束，輸出 count + 字元
                sb.append(count).append(s.charAt(i - 1));
                count = 1; // 重置
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 題目定義 countAndSay(1) = "1"。
2. 從 2 開始，逐步根據前一項進行 Run-Length Encoding (RLE)：
   - 遍歷字串，計算連續相同字元的長度 count。
   - 當 run 結束時，輸出「count + 字元」。
3. 疊代 n-1 次即可得到答案。
4. 範例：
   n=1 → "1"
   n=2 → "11"  (一個 1)
   n=3 → "21"  (兩個 1)
   n=4 → "1211" (一個 2，一個 1)
5. 複雜度：
   - 時間：O(n * m)，m 為字串平均長度，隨 n 增長而增大，但 n ≤ 30 時可接受。
   - 空間：O(m)，用於生成新字串。
*/
