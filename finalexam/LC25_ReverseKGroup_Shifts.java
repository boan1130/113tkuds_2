package finalexam;

import java.io.*;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String kLine = br.readLine();
        if (kLine == null || kLine.trim().isEmpty()) {
            System.out.println();
            return;
        }
        int k = Integer.parseInt(kLine.trim());
        
        String seqLine = br.readLine();
        if (seqLine == null || seqLine.trim().isEmpty()) {
            System.out.println();
            return;
        }
        
        String[] parts = seqLine.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (String p : parts) {
            cur.next = new ListNode(Integer.parseInt(p));
            cur = cur.next;
        }
        
        ListNode head = dummy.next;
        head = reverseKGroup(head, k);

        // 輸出
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(' ');
            head = head.next;
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }

    // 主函式：逐段檢查、反轉
    static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            // 找到這一組的尾端
            ListNode end = prev;
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break; // 不足 k，停止

            ListNode start = prev.next;
            ListNode nextGroup = end.next;

            // 反轉這段 [start, end]
            reverse(start, end);

            // 接回來
            prev.next = end;
            start.next = nextGroup;

            // 移動 prev 到下一組前
            prev = start;
        }

        return dummy.next;
    }

    // 區段反轉 [start, end] (inclusive)
    static void reverse(ListNode start, ListNode end) {
        ListNode prev = null, cur = start;
        ListNode stop = end.next; // 反轉到 end 為止
        while (cur != stop) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
    }
}