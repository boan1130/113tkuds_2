package finalexam;

import java.io.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            System.out.println("-1 -1");
            return;
        }
        String[] nt = first.trim().split("\\s+");
        int n = Integer.parseInt(nt[0]);
        int target = Integer.parseInt(nt[1]);

        if (n == 0) {
            System.out.println("-1 -1");
            return;
        }

        String second = br.readLine();
        if (second == null || second.trim().isEmpty()) {
            System.out.println("-1 -1");
            return;
        }
        String[] parts = second.trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);

        int[] ans = searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }

    static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = lowerBound(nums, target + 1) - 1;
        return new int[]{left, right};
    }

    // lower_bound: 回傳第一個 >= target 的索引
    static int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
