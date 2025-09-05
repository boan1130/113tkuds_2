package finalexam;

import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nt = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(nt[0]);
        int target = Integer.parseInt(nt[1]);

        String[] parts = br.readLine().trim().split("\\s+");
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = Integer.parseInt(parts[i]);

        Arrays.sort(candidates); // 排序後才好去重
        backtrack(candidates, target, 0);

        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    static void backtrack(int[] nums, int remain, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 同層去重
            if (nums[i] > remain) break; // 剪枝

            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i + 1); // 只能用一次，所以 i+1
            path.remove(path.size() - 1);
        }
    }
}   