package finalexam;

import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        int[] result = twoSum(seats, target);

        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * 使用 HashMap 來記錄「還需要的座位數」對應的索引
     * 時間複雜度 O(n)，空間 O(n)
     */
    private static int[] twoSum(long[] seats, long target) {
        // key: 還需要的數字，value: 對應的索引
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < seats.length; i++) {
            long current = seats[i];

            // 如果有人在等 current，直接完成
            if (map.containsKey(current)) {
                return new int[]{map.get(current), i};
            }

            // 否則記錄還需要 target - current
            long need = target - current;
            map.put(need, i);
        }

        // 如果沒有找到任何解
        return new int[]{-1, -1};
    }
}
