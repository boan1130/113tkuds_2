package midterm;

import java.io.*;
import java.util.*;
public class M10_RBPropertiesCheck {
    static int n;
    static int[] val;
    static char[] color; // 'B' or 'R'，對於 val=-1 的 NIL 位置一律視為 'B'

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String tok = fs.next();
        if (tok == null) return;
        n = Integer.parseInt(tok);

        val = new int[n];
        color = new char[n];

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(fs.next()); // value
            String c = fs.next();                 // color token: "B" or "R"
            val[i] = v;
            if (v == -1) color[i] = 'B';         // NIL 一律當黑
            else          color[i] = (c.charAt(0) == 'R' ? 'R' : 'B');
        }

        // 性質 1：根節點為黑（空樹視為合法：根為 NIL，當黑）
        if (n > 0 && val[0] != -1 && color[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 性質 2：不得有相鄰紅節點（紅紅相鄰）
        int viol = firstRedRedViolationIndex();
        if (viol != -1) {
            System.out.println("RedRedViolation at index " + viol);
            return;
        }

        // 性質 3：黑高度一致（後序、疊代）
        if (!checkBlackHeightConsistent()) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // 回傳第一個（最小索引）出現「父紅且子紅」的父節點索引；若無回傳 -1
    private static int firstRedRedViolationIndex() {
        for (int i = 0; i < n; i++) {
            if (!exists(i)) continue;
            if (color[i] == 'R') {
                int l = left(i), r = right(i);
                if (exists(l) && color[l] == 'R') return i;
                if (exists(r) && color[r] == 'R') return i;
            }
        }
        return -1;
    }

    // 檢查黑高度一致：對所有節點，其左右子樹的 black-height 必須相等。
    // 定義：bh(NIL) = 1（NIL 視為黑並計入黑高度）
    // 若節點為黑，bh(node) = 1 + bh(child)；若紅，bh(node) = bh(child)
    private static boolean checkBlackHeightConsistent() {
        if (n == 0 || val[0] == -1) return true; // 空樹視為合法

        int[] bh = new int[n]; // 僅對存在的節點填值
        Deque<State> st = new ArrayDeque<>();
        st.push(new State(0, false));

        while (!st.isEmpty()) {
            State cur = st.pop();
            int i = cur.idx;
            if (!exists(i)) continue; // NIL 的 bh 在父節點用 1 直接帶入

            if (!cur.visited) {
                cur.visited = true;
                st.push(cur); // 回來再計算
                int l = left(i), r = right(i);
                if (exists(r)) st.push(new State(r, false));
                if (exists(l)) st.push(new State(l, false));
            } else {
                int l = left(i), r = right(i);
                int bl = exists(l) ? bh[l] : 1; // NIL -> 1
                int br = exists(r) ? bh[r] : 1;
                if (bl != br) return false;     // 黑高度不一致
                bh[i] = bl + (color[i] == 'B' ? 1 : 0);
            }
        }
        return true;
    }

    // 工具：是否為實體節點（非 NIL 且在界內）
    private static boolean exists(int i) {
        return i >= 0 && i < n && val[i] != -1;
    }

    private static int left(int i)  { return 2 * i + 1; }
    private static int right(int i) { return 2 * i + 2; }

    // 疊代後序的狀態
    static class State {
        int idx; boolean visited;
        State(int i, boolean v) { idx = i; visited = v; }
    }

    // 輕量讀取（token-based）
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
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}