/**
* ---- 計算複雜度 ----
 * 假設有 n 筆收入，每筆收入需依級距分段累加。
 * - 每筆收入最多經過 4 個區間 → O(1)
 * - 總時間複雜度：O(n)
 * - 額外空間複雜度：O(1)（只需計算中的變數）
 */
package midterm;

import java.io.*;
import java.util.*;
public class M04_TieredTaxSimple {

    // 各稅率區間上下界 & 稅率
    private static final int[] lower = {0, 120001, 500001, 1000001};
    private static final int[] upper = {120000, 500000, 1000000, Integer.MAX_VALUE};
    private static final double[] rate = {0.05, 0.12, 0.20, 0.30};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        long totalTax = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine().trim());
            long tax = computeTax(income);
            sb.append("Tax: ").append(tax).append('\n');
            totalTax += tax;
        }

        long avg = totalTax / n;
        sb.append("Average: ").append(avg).append('\n');

        System.out.print(sb.toString());
    }

    // 計算單筆收入的稅額
    private static long computeTax(long income) {
        long tax = 0;
        for (int i = 0; i < lower.length; i++) {
            if (income >= lower[i]) {
                long hi = Math.min(income, upper[i]);
                long amount = hi - lower[i] + 1; // +1 因為下界含
                tax += Math.round(amount * rate[i]);
            }
        }
        return tax;
    }
}