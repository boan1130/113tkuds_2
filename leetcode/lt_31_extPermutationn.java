// 題目：Next Permutation
// 給定一個整數陣列 nums，請將其重新排列為字典序中「下一個更大的排列」。
// 若無法找到更大的排列，則將陣列重新排列為「最小的排列」（遞增排序）。
class Solution {
    public void extPermutationn(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: 從右往左找第一個遞增的位置 (pivot)，即 nums[i] < nums[i+1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) { 
            // Step 2: 再從右往左找第一個比 nums[i] 大的元素，與其交換
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: 反轉 i+1 之後的子序列，讓它變成最小的升序排列
        reverse(nums, i + 1, n - 1);
    }

    // 工具函式：交換陣列中的兩個元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 工具函式：反轉陣列的一段區間
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}

/*
解題思路：
1. 題目要求找到下一個「字典序更大的排列」。
2. 從右邊開始找「第一個遞增的位置 i」，代表在 i 之後還有機會變大。
   - 若沒找到 (nums 整體為遞減)，表示已經是最後一個排列 → 直接整個反轉成最小序列。
3. 找到 i 後，往右邊尋找第一個比 nums[i] 大的數 j，交換兩者。
   - 這樣保證排列變大，但只比原本大一點點。
4. 最後把 i 之後的子陣列反轉，讓它從大到小變回最小的升序排列。
5. 複雜度：
   - 時間：O(n)，最多掃兩次陣列。
   - 空間：O(1)，只用到常數變數與交換操作。
*/
