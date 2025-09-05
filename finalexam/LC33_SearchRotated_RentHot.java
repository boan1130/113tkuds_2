package finalexam;

import java.io.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            System.out.println(-1);
            return;
        }
        String[] nt = first.trim().split("\\s+");
        int n = Integer.parseInt(nt[0]);
        int target = Integer.parseInt(nt[1]);

        String second = br.readLine();
        if (second == null || second.trim().isEmpty()) {
            System.out.println(-1);
            return;
        }
        String[] parts = second.trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);

        int ans = search(nums, target);
        System.out.println(ans);
    }

    static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;

            // 左半有序
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1; // target 在左半
                } else {
                    l = mid + 1; // target 在右半
                }
            }
            // 右半有序
            else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1; // target 在右半
                } else {
                    r = mid - 1; // target 在左半
                }
            }
        }
        return -1;
    }
}