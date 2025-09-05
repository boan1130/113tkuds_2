package finalexam;


import java.io.*;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        String[] parts = line.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (String p : parts) {
            cur.next = new ListNode(Integer.parseInt(p));
            cur = cur.next;
        }

        ListNode head = dummy.next;
        head = swapPairs(head);

        // 輸出
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(' ');
            head = head.next;
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }

    // 成對交換
    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 調整指標 (a,b) → (b,a)
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 前進到下一對
            prev = a;
        }
        return dummy.next;
    }
}
