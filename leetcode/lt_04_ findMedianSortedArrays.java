class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 總是在較短的陣列上二分
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半邊總數（含中位數）

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) / 2;          // 切 nums1 左側取 i 個
            int j = totalLeft - i;          // 切 nums2 左側取 j 個

            int Aleft  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int Bleft  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 找到合法切割：左邊都 <= 右邊
            if (Aleft <= Bright && Bleft <= Aright) {
                if (((m + n) & 1) == 1) {
                    // 奇數：左半最大
                    return Math.max(Aleft, Bleft);
                } else {
                    // 偶數：左半最大與右半最小平均
                    int leftMax = Math.max(Aleft, Bleft);
                    int rightMin = Math.min(Aright, Bright);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (Aleft > Bright) {
                // i 太大，往左縮
                hi = i - 1;
            } else {
                // Aright < Bleft，i 太小，往右擴
                lo = i + 1;
            }
        }
        // 理論上不會到這裡（輸入符合條件）
        throw new IllegalArgumentException("Invalid input");
    }
}
