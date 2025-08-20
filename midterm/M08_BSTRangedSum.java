package midterm;

import java.io.*;
import java.util.*;
public class M08_BSTRangedSum {

    // 二元樹節點
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readNonEmpty(br));
        int[] arr = new int[n];

        // 讀層序陣列
        StringTokenizer st = new StringTokenizer(readNonEmpty(br));
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 讀 L R
        st = new StringTokenizer(readNonEmpty(br));
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Node root = buildTree(arr);
        long sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }

    // 依層序建樹：-1 代表 null
    private static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (cur == null) continue;

            // 左
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;

            // 右
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // 迭代 DFS + 剪枝 計算區間和
    private static long rangeSumBST(Node root, int L, int R) {
        if (root == null) return 0;
        long sum = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur == null) continue;

            if (cur.val < L) {
                // 只可能在右子樹
                if (cur.right != null) stack.push(cur.right);
            } else if (cur.val > R) {
                // 只可能在左子樹
                if (cur.left != null) stack.push(cur.left);
            } else {
                // 在 [L, R] 內
                sum += cur.val;
                if (cur.left != null) stack.push(cur.left);
                if (cur.right != null) stack.push(cur.right);
            }
        }
        return sum;
    }

    // 讀下一個非空白行
    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        throw new EOFException("Unexpected end of input");
    }
}