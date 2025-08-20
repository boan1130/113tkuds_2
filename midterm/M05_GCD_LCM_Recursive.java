/**
* ---- 計算複雜度 ----
 * GCD 的時間複雜度：O(log(min(a,b)))，這是歐幾里得演算法的特性。
 * LCM 的計算：O(1)。
 * 總時間：O(log(min(a,b)))。
 * 額外空間：遞迴深度最多 O(log(min(a,b)))。
 */
package midterm;

import java.io.*;
import java.util.*;
public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long g = gcd(a, b);
        long l = (a / g) * b; // 先除後乘，避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴歐幾里得
    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}   