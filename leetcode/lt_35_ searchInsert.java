// 題目：Search Insert Position
// 已知一個升序、互異的整數陣列 nums 與一個目標值 target。
// 若找到 target，回傳其索引；若沒找到，回傳 target 應插入的位置索引。
// 要求：時間複雜度 O(log n)。

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到直接回傳
            } else if (nums[mid] < target) {
                left = mid + 1; // target 在右邊
            } else {
                right = mid - 1; // target 在左邊
            }
        }

        // 若沒找到，left 即為 target 應插入的位置
        return left;
    }
}

/*
解題思路：
1. 使用二分搜尋：
   - 若 nums[mid] == target，回傳 mid。
   - 若 nums[mid] < target，表示 target 在右邊 → left = mid + 1。
   - 若 nums[mid] > target，表示 target 在左邊 → right = mid - 1。
2. 當迴圈結束還沒找到 target 時，left 就是 target 應插入的位置：
   - 例：nums = [1,3,5,6], target = 2
     搜尋結束時 left = 1 → 插入索引 1。
   - 例：nums = [1,3,5,6], target = 7
     搜尋結束時 left = 4 → 插入尾端。
3. 範例：
   - [1,3,5,6], target=5 → 2
   - [1,3,5,6], target=2 → 1
   - [1,3,5,6], target=7 → 4
4. 複雜度：
   - 時間：O(log n)，二分搜尋。
   - 空間：O(1)，只用常數變數。
*/
