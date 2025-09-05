package finalexam;

import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class Node {
        int val;
        int listIdx;   // 來自哪個串列
        int pos;       // 在該串列的索引
        Node(int v, int li, int p) {
            val = v;
            listIdx = li;
            pos = p;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int k = fs.nextInt();
        if (k == 0) {
            System.out.println();
            return;
        }

        // 先把輸入存成陣列方便存取
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            List<Integer> tmp = new ArrayList<>();
            while (true) {
                int x = fs.nextInt();
                if (x == -1) break;
                tmp.add(x);
            }
            int[] arr = tmp.stream().mapToInt(Integer::intValue).toArray();
            lists.add(arr);
        }

        // 最小堆 (值、串列idx、位置)，同值時依 listIdx, pos 維持穩定性
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.val != b.val) return a.val - b.val;
            if (a.listIdx != b.listIdx) return a.listIdx - b.listIdx;
            return a.pos - b.pos;
        });

        // 初始把每條非空串列的頭丟進堆
        for (int i = 0; i < k; i++) {
            if (lists.get(i).length > 0) {
                pq.add(new Node(lists.get(i)[0], i, 0));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            sb.append(cur.val).append(' ');

            int nextPos = cur.pos + 1;
            if (nextPos < lists.get(cur.listIdx).length) {
                pq.add(new Node(lists.get(cur.listIdx)[nextPos], cur.listIdx, nextPos));
            }
        }

        if (sb.length() > 0) sb.setLength(sb.length() - 1); // 去掉尾端空格
        System.out.println(sb.toString());
    }

    // 快速讀取器
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream in) { br = new BufferedReader(new InputStreamReader(in)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}