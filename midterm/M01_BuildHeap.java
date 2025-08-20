// ---- 計算複雜度 ----
// 時間複雜度：O(n)
//   證明概要：heapifyDown 的成本與節點高度成正比。深度 d 的節點有約 n/2^(d+1) 個，
//   每個最多下沉 O(H-d) 次。加總結果 ≤ n * Σ(k/2^k) = O(n)。
//   這比逐一 insert 的 O(n log n) 更快。
// 空間複雜度：O(1)
//   除了輸入陣列外，僅用到常數額外變數，原地建堆。
//
// ---- 穩定性說明 ----
// 相等元素時不額外換位，保持原始相對順序（左子優先）。
//
// ---- 使用限制 ----
// n ≤ 1e5；不得用 PriorityQueue。

package midterm;
import java.io.*;
import java.util.*;

public class M01_BuildHeap {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String type = fs.next();       // "max" or "min"
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(fs.next());

        boolean isMax = type.equalsIgnoreCase("max");

        // Bottom-up heapify: 從最後一個非葉節點開始
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(a, n, i, isMax);
        }

        // Output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    // heapifyDown：把節點 i 往下沉以恢復堆性質
    private static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int left = 2 * i + 1;
            int right = left + 1;
            int target = i;

            if (isMax) {
                if (left < n && a[left] > a[target]) target = left;
                if (right < n && a[right] > a[target]) target = right;
                if (target != i) {
                    swap(a, i, target);
                    i = target;
                } else break;
            } else {
                if (left < n && a[left] < a[target]) target = left;
                if (right < n && a[right] < a[target]) target = right;
                if (target != i) {
                    swap(a, i, target);
                    i = target;
                } else break;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // Fast scanner for up to 1e5 integers quickly.
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}