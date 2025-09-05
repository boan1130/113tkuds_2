package finalexam;


import java.io.*;
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nLine = br.readLine();
        if (nLine == null || nLine.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        int n = Integer.parseInt(nLine.trim());
        if (n == 0) {
            System.out.println(0);
            return;
        }

        String seqLine = br.readLine();
        if (seqLine == null || seqLine.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        String[] parts = seqLine.trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);

        int newLen = removeDuplicates(arr);

        // 輸出結果
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(arr[i]);
            if (i < newLen - 1) System.out.print(" ");
        }
        System.out.println();
    }

    // 就地刪重：返回新長度，前 newLen 個元素是唯一的
    static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1; // 從 index=1 開始寫
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}
