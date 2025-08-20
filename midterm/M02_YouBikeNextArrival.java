package midterm;

import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] mins = new int[n];
        for (int i = 0; i < n; i++) {
            String t = br.readLine().trim();
            mins[i] = toMinutes(t);
        }

        String queryStr = br.readLine().trim();
        int q = toMinutes(queryStr);

        int idx = upperBound(mins, q); // 第一個 > q 的索引
        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHmm(mins[idx]));
        }
    }

    // 將 "HH:mm" 轉為自 00:00 起算的總分鐘數
    private static int toMinutes(String hhmm) {
        int h = Integer.parseInt(hhmm.substring(0, 2));
        int m = Integer.parseInt(hhmm.substring(3, 5));
        return h * 60 + m;
    }

    // 將分鐘數轉回 "HH:mm"（補零）
    private static String toHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // 上界（upper bound）：回傳第一個 > target 的索引；若不存在回傳 n
    private static int upperBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] <= target) l = mid + 1; // 想要嚴格大於，<= 的都捨棄
            else r = mid;
        }
        return l; // 可能為 n（表示沒有更晚的班次）
    }
}