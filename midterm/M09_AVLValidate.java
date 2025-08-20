/**
* ---- 計算複雜度 ----
 * 建樹：O(n)
 * 檢 BST：O(n)（每節點檢一次，疊代帶上下界）
 * 檢 AVL：O(n)（疊代後序計算高度與平衡因子）
 * 總時間：O(n)
 * 額外空間：O(n)（建樹用佇列，檢查用疊代堆疊與高度表；避免深遞迴 StackOverflow）
 */
package midterm;

import java.io.*;
import java.util.*;
public class M09_AVLValidate {

    // 二元樹節點
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    // pair 給 BST 檢查：節點 + 其允許的 (min, max) 範圍（皆為開區間）
    static class BPair {
        Node node;
        long lo, hi;
        BPair(Node n, long lo, long hi) { this.node = n; this.lo = lo; this.hi = hi; }
    }

    // pair 給 AVL 後序：節點 + 訪問旗標
    static class VPair {
        Node node;
        boolean visited;
        VPair(Node n, boolean v) { node = n; visited = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = Integer.parseInt(fs.next());
        int[] arr = new int[n];
        // 讀 n 個整數（可能跨多行）
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(fs.next());
        }

        Node root = buildTree(arr);

        // 先檢查 BST
        if (!isBSTIter(root)) {
            System.out.println("Invalid BST");
            return;
        }
        // 再檢查 AVL
        if (!isAVLIter(root)) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }

    // 由層序（-1 表 null）建樹
    private static Node buildTree(int[] a) {
        if (a.length == 0 || a[0] == -1) return null;
        Node root = new Node(a[0]);
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < a.length) {
            Node cur = q.poll();
            if (cur == null) continue;

            // 左子
            if (i < a.length && a[i] != -1) {
                cur.left = new Node(a[i]);
                q.offer(cur.left);
            }
            i++;

            // 右子
            if (i < a.length && a[i] != -1) {
                cur.right = new Node(a[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // 疊代檢查 BST：對每個節點帶上下界（開區間）
    private static boolean isBSTIter(Node root) {
        if (root == null) return true;
        Deque<BPair> st = new ArrayDeque<>();
        st.push(new BPair(root, Long.MIN_VALUE, Long.MAX_VALUE));
        while (!st.isEmpty()) {
            BPair p = st.pop();
            Node u = p.node;
            long lo = p.lo, hi = p.hi;
            if (u == null) continue;
            // 嚴格不允許重複鍵：lo < val < hi
            if (!(lo < u.val && u.val < hi)) return false;
            if (u.right != null) st.push(new BPair(u.right, u.val, hi));
            if (u.left  != null) st.push(new BPair(u.left , lo, u.val));
        }
        return true;
    }

    // 疊代檢查 AVL：後序遍歷，自底向上計高度並檢 BF
    private static boolean isAVLIter(Node root) {
        if (root == null) return true;
        Deque<VPair> st = new ArrayDeque<>();
        Map<Node, Integer> height = new IdentityHashMap<>(); // Node 物件做 key

        st.push(new VPair(root, false));
        boolean ok = true;

        while (!st.isEmpty() && ok) {
            VPair p = st.pop();
            Node u = p.node;
            if (u == null) continue;

            if (!p.visited) {
                // 第一次看到：改為 visited 再壓回去，並先壓右、左（後序）
                p.visited = true;
                st.push(p);
                st.push(new VPair(u.right, false));
                st.push(new VPair(u.left , false));
            } else {
                // 回來時左右高度已就緒
                int hl = height.getOrDefault(u.left , 0);
                int hr = height.getOrDefault(u.right, 0);
                int bf = hl - hr;
                if (bf < -1 || bf > 1) {
                    ok = false;
                    break;
                }
                height.put(u, Math.max(hl, hr) + 1);
            }
        }
        return ok;
    }

    // 輕量快速讀取
    static class FastScanner {
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
            // skip spaces/newlines
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            // read token
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}