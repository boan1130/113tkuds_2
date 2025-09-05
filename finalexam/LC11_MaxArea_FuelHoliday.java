package finalexam;

import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextLong();
        }
        System.out.println(maxArea(heights));
    }

    /**
     * 雙指針法：從最左和最右開始，逐步內縮
     * 只移動較短的一邊，因為長邊不動不會提升 min 高度
     * 時間 O(n)，空間 O(1)
     */
    private static long maxArea(long[] heights) {
        int left = 0, right = heights.length - 1;
        long max = 0;

        while (left < right) {
            long width = right - left;
            long minHeight = Math.min(heights[left], heights[right]);
            max = Math.max(max, width * minHeight);

            // 移動較短的一邊
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
