// 題目：Sudoku Solver
// 已知一個 9x9 的數獨棋盤，其中 '.' 代表空格。
// 請填入數字 1-9，使棋盤成為一個有效的數獨解答。
// 條件：每行、每列、每個 3x3 小區塊都必須包含 1-9，且不能重複。

class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    // 回溯法主體
    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // 嘗試填入 1-9
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // 放入數字
                            if (backtrack(board)) {
                                return true; // 成功解出
                            }
                            board[i][j] = '.'; // 回溯，撤銷選擇
                        }
                    }
                    return false; // 9 個數字都嘗試過仍無解 → 回溯
                }
            }
        }
        return true; // 所有格子都填完了 → 找到解答
    }

    // 驗證某數字能否放入 (i, j)
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int k = 0; k < 9; k++) {
            // 檢查行
            if (board[row][k] == c) return false;
            // 檢查列
            if (board[k][col] == c) return false;
            // 檢查 3x3 小區塊
            int boxRow = 3 * (row / 3) + k / 3;
            int boxCol = 3 * (col / 3) + k % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}

/*
解題思路：
1. 採用回溯法 (Backtracking)：
   - 遍歷整個棋盤，遇到 '.' 就嘗試放入 '1'~'9'。
   - 放入之前需檢查該數字是否符合數獨規則 (行、列、3x3 區塊不得重複)。
   - 若能放入，則遞迴繼續解下去；若無法繼續，則回溯，撤銷剛剛的選擇。
2. 當所有格子都填滿，即找到解答。
3. 因題目保證有唯一解，找到後可立即返回。
4. 複雜度分析：
   - 時間：最壞情況接近 O(9^(n))，其中 n 為空格數，但實際上剪枝效果很好。
   - 空間：O(81) = O(1)，僅使用遞迴棧與常數額外空間。
*/
