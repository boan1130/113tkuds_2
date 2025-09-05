package finalexam;


import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {
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

        Arrays.sort(candidates); // 排序方便剪枝
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
            if (nums[i] > remain) break; // 剪枝
            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i); // 可重複使用 i
            path.remove(path.size() - 1);
        }
    }
}
