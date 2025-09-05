package finalexam;

import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        int k = sc.nextInt();

        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * 刪除倒數第 k 節點
     * 使用 fast-slow 雙指標，一趟完成
     * 時間 O(n)，空間 O(1)
     */
    private static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;

        // fast 先走 k+1 步，讓 slow 停在待刪的前一個
        for (int i = 0; i <= k; i++) {
            fast = fast.next;
        }

        // fast 與 slow 同步走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;

        return dummy.next;
    }
}

