package midterm;

import java.io.*;
import java.util.*;
public class M12_MergeKTimeTables {

    static class Node {
        int time;      // 以分鐘表示
        int listId;    // 來源清單
        int idx;       // 該清單中的索引
        Node(int t, int l, int i) { time = t; listId = l; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String tok = fs.next();
        if (tok == null) return;
        int K = Integer.parseInt(tok);

        List<int[]> lists = new ArrayList<>(K);
        boolean useHHmm = false; // 若任何一筆像 "HH:mm" 則全程用 HH:mm

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(fs.next());
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String t = fs.next();
                if (t.indexOf(':') >= 0) useHHmm = true;
                arr[j] = parseTimeToMinutes(t);
            }
            lists.add(arr);
        }

        // Min-Heap：time 升序；平手以 (listId, idx) 升序
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override public int compare(Node a, Node b) {
                if (a.time != b.time) return Integer.compare(a.time, b.time);
                if (a.listId != b.listId) return Integer.compare(a.listId, b.listId);
                return Integer.compare(a.idx, b.idx);
            }
        });

        // 將每個清單第一個推入
        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            first = false;
            out.append(useHHmm ? toHHmm(cur.time) : String.valueOf(cur.time));

            // 同來源推入下一個
            int[] src = lists.get(cur.listId);
            int nextIdx = cur.idx + 1;
            if (nextIdx < src.length) {
                pq.offer(new Node(src[nextIdx], cur.listId, nextIdx));
            }
        }

        System.out.println(out.toString());
    }

    // 支援 "H:mm" 或 "HH:mm" 或 純數字（分鐘）。輸入若為整數分鐘直接回傳。
    private static int parseTimeToMinutes(String s) {
        if (s.indexOf(':') < 0) {
            // 純分鐘
            return Integer.parseInt(s);
        }
        // HH:mm 或 H:mm
        int colon = s.indexOf(':');
        int h = Integer.parseInt(s.substring(0, colon));
        int m = Integer.parseInt(s.substring(colon + 1));
        if (h < 0 || h > 23 || m < 0 || m > 59)
            throw new IllegalArgumentException("Invalid time: " + s);
        return h * 60 + m;
    }

    // 分鐘轉 "HH:mm"
    private static String toHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // 輕量 token 掃描
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char)c); c = read(); } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}