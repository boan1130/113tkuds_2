class Solution {
    // 題目：Container With Most Water
    // 給定每根豎線的高度，在不傾斜容器的前提下，找出能裝最多水的兩根線所形成的容器面積
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;

        // 夾擠雙指針：每次計算當前容器面積，並移動較短的那一側（期望遇到更高的牆）
        while (left < right) {
            int h = Math.min(height[left], height[right]);   // 容器高度由短板決定
            int w = right - left;                            // 寬度是兩指針距離
            ans = Math.max(ans, h * w);

            // 核心貪心：移動較短的一側，才可能使最小高度提升，面積才有機會變大
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 兩指針 left=0、right=n-1 代表當前考慮的兩根線。
2. 面積 = min(height[left], height[right]) * (right - left)。
3. 為了讓面積變大，在固定寬度縮小一格時，唯一能補償的是「高度提升」；
   因此移動較短的那根線（短板效應），期望遇到更高的線。
4. 不需要窮舉所有組合（O(n^2)），用這個貪心+雙指針能保證在 O(n) 找到最優解。

時間複雜度：O(n)  —— 每個指針最多各移動 n 次。
空間複雜度：O(1)  —— 只用到常數額外空間。
*/
