// 題目：Combination Sum II
// 已知一組候選數字 candidates 與目標值 target。
// 每個數字「最多只能用一次」，找出所有總和等於 target 的「不重複組合」。

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序，方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(path)); // 收錄答案
            return;
        } else if (remain < 0) {
            return; // 超過 target → 回溯
        }

        for (int i = start; i < candidates.length; i++) {
            // 跳過相同數字，避免重複組合
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]); // 選擇 candidates[i]
            // 每個數字只能用一次 → 傳 i+1
            backtrack(candidates, remain - candidates[i], i + 1, path, result);
            path.remove(path.size() - 1); // 回溯，撤銷選擇
        }
    }
}

/*
解題思路：
1. 與 Combination Sum I 不同，本題要求每個數字只能用一次。
2. 為了避免重複組合，先對 candidates 排序：
   - 例如 [1,1,6] 只允許第一個 1 開始組合，後面相同的 1 跳過。
3. 遞迴：
   - path：當前組合
   - remain：剩餘目標
   - start：控制從哪個位置開始選，避免重複使用相同元素
4. 當 remain == 0 → 收錄答案。
   當 remain < 0 → 提前結束。
5. 範例：
   - candidates=[10,1,2,7,6,1,5], target=8
     → [1,1,6], [1,2,5], [1,7], [2,6]
   - candidates=[2,5,2,1,2], target=5
     → [1,2,2], [5]
6. 複雜度：
   - 時間：指數級 O(2^n)，取決於 target 與 candidates。
   - 空間：O(n)，遞迴深度與暫存 path。
*/
