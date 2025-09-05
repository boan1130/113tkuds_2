// 題目：Find First and Last Position of Element in Sorted Array
// 已知一個非遞減排序的整數陣列 nums，請回傳目標值 target 的「起始索引與結束索引」。
// 若 target 不存在，回傳 [-1, -1]。
// 要求：時間複雜度 O(log n)。

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);   // 找第一個出現的位置
        int last = findBound(nums, target, false);  // 找最後一個出現的位置
        return new int[]{first, last};
    }

    // 輔助函式：使用二分搜尋找邊界
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid; // 記錄當前匹配位置
                if (isFirst) {
                    // 繼續往左邊找，直到最左邊界
                    right = mid - 1;
                } else {
                    // 繼續往右邊找，直到最右邊界
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}

/*
解題思路：
1. 題目要求在排序陣列中找 target 的起始與結束索引。
2. 使用二分搜尋，分別找：
   - 第一個出現的位置 (first bound)。
   - 最後一個出現的位置 (last bound)。
3. findBound 函式：
   - 當找到 nums[mid] == target 時，先記錄位置，再依 isFirst 決定繼續往左或往右找。
   - 這樣可以確保最終找到的是邊界。
4. 若 target 不存在，返回 [-1, -1]。
5. 範例：
   - [5,7,7,8,8,10], target=8 → [3,4]
   - [5,7,7,8,8,10], target=6 → [-1,-1]
   - [], target=0 → [-1,-1]
6. 複雜度：
   - 時間：O(log n)，二分搜尋兩次。
   - 空間：O(1)，只用常數變數。
*/
