/**
* ---- 計算複雜度 ----
 * 建堆（Bottom-up）：O(n)
 * Heapsort（n 次 extract-max + 堆化）：O(n log n)
 * 總時間：O(n log n)
 * 額外空間：O(1)（就地排序，以平行陣列存放索引）
 */
package midterm;

import java.io.*;
import java.util.*;
public class M11_HeapSortWithTie {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];    // scores
        int[] idx = new int[n];  // original indices

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(fs.next());
            idx[i] = i;
        }

        // 1) Bottom-up 建 Max-Heap（依比較規則）
        for (int i = (n / 2) - 1; i >= 0; i--) {
            siftDown(a, idx, n, i);
        }

        // 2) Heapsort：把最大值依序放到尾端
        for (int end = n - 1; end > 0; end--) {
            swap(a, idx, 0, end);
            siftDown(a, idx, end, 0);
        }

        // 輸出：遞增序（heapsort 產生的陣列即遞增）
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    // Max-Heap 的下沉；堆大小為 heapSize，從 i 開始
    private static void siftDown(int[] a, int[] idx, int heapSize, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = left + 1;
            int largest = i;

            if (left < heapSize && greater(a, idx, left, largest)) largest = left;
            if (right < heapSize && greater(a, idx, right, largest)) largest = right;

            if (largest != i) {
                swap(a, idx, i, largest);
                i = largest;
            } else break;
        }
    }

    // 比較規則：score 大者大；同分時 idx 大者大（讓同分索引小的最後排在前面）
    private static boolean greater(int[] a, int[] idx, int i, int j) {
        if (a[i] != a[j]) return a[i] > a[j];
        return idx[i] > idx[j];
    }

    private static void swap(int[] a, int[] idx, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        int ti = idx[i]; idx[i] = idx[j]; idx[j] = ti;
    }

    // 輕量快速讀取（支援一行多數字）
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
            do { sb.append((char) c); c = read(); } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}