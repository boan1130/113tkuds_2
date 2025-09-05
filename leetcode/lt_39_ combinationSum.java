// 題目：Combination Sum
// 已知一組互異的整數 candidates 和目標值 target。
// 請找出所有「可重複使用數字」的組合，使其總和等於 target。
// 回傳所有組合（順序不限）。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // 回溯函式
    private void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> result) {
        if (remain == 0) {
            // 剛好湊到 target，收錄答案
            result.add(new ArrayList<>(path));
            return;
        } else if (remain < 0) {
            // 超過 target，提前結束
            return;
        }

        // 從 start 開始，避免重複組合
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]); // 選擇 candidates[i]
            // 因為可以重複選，所以遞迴時仍從 i 開始
            backtrack(candidates, remain - candidates[i], i, path, result);
            path.remove(path.size() - 1); // 回溯，撤銷選擇
        }
    }
}

/*
解題思路：
1. 這是一個典型「組合總和」問題，可用回溯法：
   - 路徑 path：目前選到的數字組合。
   - remain：剩餘目標值。
   - start：控制從哪個索引開始選，避免重複組合。
2. 遞迴過程：
   - 若 remain == 0，表示湊到 target，收錄答案。
   - 若 remain < 0，表示超過 target，直接回溯。
   - 否則遍歷 candidates，選擇一個數字後繼續往下探索。
3. 因為「同一數字可以用多次」，所以遞迴時仍傳 i，而不是 i+1。
4. 範例：
   - candidates=[2,3,6,7], target=7
     → [2,2,3], [7]
   - candidates=[2,3,5], target=8
     → [2,2,2,2], [2,3,3], [3,5]
5. 複雜度：
   - 時間：指數級 O(2^n)，取決於 target 與 candidates 大小。
   - 空間：O(target)，取決於遞迴深度。
*/
