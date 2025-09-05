// 題目：Search in Rotated Sorted Array
// 已知 nums 為「可能被旋轉」的升序整數陣列，且數字互不重複。
// 請回傳目標值 target 在陣列中的索引，若不存在則回傳 -1。
// 要求：時間複雜度 O(log n)。

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到目標值直接回傳
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷哪一半是「有序」的
            if (nums[left] <= nums[mid]) {
                // 左半邊有序
                if (nums[left] <= target && target < nums[mid]) {
                    // target 落在左半區間
                    right = mid - 1;
                } else {
                    // target 不在左半 → 搜右半
                    left = mid + 1;
                }
            } else {
                // 右半邊有序
                if (nums[mid] < target && target <= nums[right]) {
                    // target 落在右半區間
                    left = mid + 1;
                } else {
                    // target 不在右半 → 搜左半
                    right = mid - 1;
                }
            }
        }
        return -1; // 找不到
    }
}

/*
解題思路：
1. nums 是「升序陣列被旋轉」的版本 → 可以分成「有序的一半」與「可能被切斷的一半」。
2. 使用二分搜尋：
   - 每次檢查中間值 nums[mid]。
   - 若 nums[left] <= nums[mid]，代表左半段是有序的：
     - 如果 target 在 [nums[left], nums[mid]) 範圍內 → 往左搜；
     - 否則往右搜。
   - 否則代表右半段是有序的：
     - 如果 target 在 (nums[mid], nums[right]] 範圍內 → 往右搜；
     - 否則往左搜。
3. 重複直到找到目標或區間縮小為空。
4. 複雜度：
   - 時間：O(log n)，因為是二分搜尋。
   - 空間：O(1)，只用常數變數。
*/
