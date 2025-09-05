package finalexam;

import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();

        double median = findMedianSortedArrays(A, B);
        System.out.printf("%.1f\n", median);  // 保留 1 位小數
    }

    /**
     * O(log(min(n,m))) 演算法：二分切割
     */
    private static double findMedianSortedArrays(double[] A, double[] B) {
        // 保證 A 是短的
        if (A.length > B.length) return findMedianSortedArrays(B, A);

        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2; // 左半部分總數量

        int lo = 0, hi = n;  // 在 A 中二分
        while (lo <= hi) {
            int i = (lo + hi) / 2;       // A 左半取 i 個
            int j = totalLeft - i;       // B 左半取 j 個

            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            // 驗證切割是否正確
            if (Aleft <= Bright && Bleft <= Aright) {
                // 符合條件，計算中位數
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                hi = i - 1; // A 左邊太大，往左縮
            } else {
                lo = i + 1; // A 左邊太小，往右擴
            }
        }
        throw new IllegalArgumentException("Input arrays not valid");
    }
}
