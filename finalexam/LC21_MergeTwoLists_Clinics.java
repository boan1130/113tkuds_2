package finalexam;

import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode l1 = buildList(sc, n);
        ListNode l2 = buildList(sc, m);

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出合併後序列
        printList(merged);
    }

    // 建立鏈結串列
    private static ListNode buildList(Scanner sc, int len) {
        if (len == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < len; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        return dummy.next;
    }

    // 輸出鏈結串列
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * 合併兩個已排序鏈結串列
     * 時間 O(n+m)，空間 O(1)
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘節點
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
    }
}
