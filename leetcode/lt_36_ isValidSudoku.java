// 題目：Valid Sudoku
// 給定 9x9 的數獨棋盤，判斷是否為「有效」的數獨。
// 條件：
// 1. 每一行 1-9 不可重複
// 2. 每一列 1-9 不可重複
// 3. 每一個 3x3 小區塊 1-9 不可重複
// 注意：只需檢查已填寫的數字 ('.' 表示空格)

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 建立三個結構：紀錄行、列、區塊中是否出現過某個數字
        boolean[][] rows = new boolean[9][9];     // rows[i][num] = true 表示第 i 行出現 num
        boolean[][] cols = new boolean[9][9];     // cols[j][num] = true 表示第 j 列出現 num
        boolean[][] boxes = new boolean[9][9];    // boxes[boxIndex][num] = true 表示某區塊出現 num

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue; // 空格跳過

                int num = c - '1'; // 將 '1'~'9' 映射到 0~8
                int boxIndex = (i / 3) * 3 + (j / 3); // 算出當前格子屬於哪個 3x3 區塊

                // 若該數字已經出現在行/列/區塊，則無效
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }

                // 標記該數字已出現
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }
}

/*
解題思路：
1. 我們要檢查棋盤的「行、列、3x3 區塊」中是否有重複數字。
2. 建立三個 9x9 的布林矩陣：
   - rows[i][num] 表示第 i 行是否出現數字 num。
   - cols[j][num] 表示第 j 列是否出現數字 num。
   - boxes[k][num] 表示第 k 個 3x3 區塊是否出現數字 num。
     (k 的計算公式：boxIndex = (i / 3) * 3 + (j / 3))
3. 遍歷整個棋盤：
   - 若是 '.' 跳過。
   - 否則將數字轉為索引 (num = c - '1')。
   - 檢查該數字是否已經出現過於「行/列/區塊」。
     - 若有重複 → 回傳 false。
     - 否則標記為已出現。
4. 若遍歷結束都沒問題 → 回傳 true。
5. 複雜度：
   - 時間：O(81) = O(1)，因為棋盤大小固定。
   - 空間：O(81) = O(1)，三個布林矩陣大小固定。
*/
