// 題目：Longest Valid Parentheses
// 給定一個只包含 '(' 和 ')' 的字串，回傳最長有效括號子字串的長度。
class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        // 使用 stack 儲存索引，初始化為 -1 作為基準點
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 左括號：索引推入堆疊
                stack.push(i);
            } else {
                // 右括號：先彈出一個索引
                stack.pop();
                if (stack.isEmpty()) {
                    // 若堆疊空了，把當前索引作為新的基準點
                    stack.push(i);
                } else {
                    // 當前有效長度 = 當前索引 - stack頂端索引
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}

/*
解題思路：
1. 題目要求最長「有效括號子字串」的長度。
2. 我們用一個 stack 來儲存「括號的索引」：
   - 當遇到 '(' 時，將索引壓入堆疊。
   - 當遇到 ')' 時，先彈出一個索引：
     - 若堆疊空了，代表目前的右括號沒有匹配，將當前索引作為「新的基準點」。
     - 若堆疊還有東西，代表有有效匹配，此時最長長度 = 當前索引 - stack頂端索引。
3. 為了方便計算長度，一開始先放入 -1 作為「基準索引」。
4. 範例：
   - s = "(()" → 最長為 "()"，長度 2。
   - s = ")()())" → 最長為 "()()"，長度 4。
5. 複雜度分析：
   - 時間：O(n)，只需一次遍歷。
   - 空間：O(n)，stack 在最壞情況下存下所有索引。
*/
