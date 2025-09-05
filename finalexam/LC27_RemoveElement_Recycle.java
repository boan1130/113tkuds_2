package finalexam;

// 檔名：LC27_RemoveElement_Recycle.java

import java.io.*;
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n 與 val
        String firstLine = br.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        String[] nv = firstLine.trim().split("\\s+");
        int n = Integer.parseInt(nv[0]);
        int val = Integer.parseInt(nv[1]);

        if (n == 0) {
            System.out.println(0);
            return;
        }

        // 讀序列
        String seqLine = br.readLine();
        if (seqLine == null || seqLine.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        String[] parts = seqLine.trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);

        int newLen = removeElement(nums, val);

        // 輸出
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) System.out.print(" ");
        }
        System.out.println();
    }

    // 移除指定元素，回傳新長度
    static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;
            }
        }
        return write;
    }
}
