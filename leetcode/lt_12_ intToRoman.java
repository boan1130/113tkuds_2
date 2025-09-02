class Solution {
    // 題目：Integer to Roman
    // 將 1..3999 的整數轉成羅馬數字（使用貪心法 + 預先列舉的值與符號）
    public String intToRoman(int num) {
        // 由大到小列出所有會用到的數值與對應符號（含減法表示法）
        int[] vals =    {1000, 900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,   1};
        String[] syms = {"M",  "CM","D", "CD", "C", "XC","L", "XL","X", "IX","V", "IV","I"};

        StringBuilder sb = new StringBuilder();
        // 逐一嘗試由大到小扣除
        for (int i = 0; i < vals.length; i++) {
            while (num >= vals[i]) {       // 能扣幾次就追加幾次符號
                num -= vals[i];
                sb.append(syms[i]);
            }
            if (num == 0) break;           // 提前結束（最佳化）
        }
        return sb.toString();
    }
}

/*
解題思路（Greedy）：
1) 預先把所有可能用到的面額與符號，包含減法規則（CM, CD, XC, XL, IX, IV）由大到小列好。
2) 從最大面額開始，能扣就扣，同時把對應的羅馬符號加入答案字串。
3) 因為羅馬數字的構成規則是「貪心最優」的（總能先用最大面額組成），
   如此即可保證正確性，且實作簡潔。

時間複雜度：O(1)
- 最大只會遍歷固定 13 個面額，每個面額扣除次數上限也有限（num ≤ 3999）。
空間複雜度：O(1)
- 僅使用固定大小的陣列與 StringBuilder。
*/
