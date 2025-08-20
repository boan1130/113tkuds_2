/**
---- 計算複雜度 ----
 * 設商品數為 n，K << n：
 *   - 每筆元素至多觸發一次 push / pop，單次 O(log K)
 *   - 總時間：O(n log K)
 *   - 額外空間：O(K)（儲存在堆中的 K 筆）
 * 若改用整體排序：O(n log n)，當 K 遠小於 n 時不如 Min-Heap 高效。
 */
package midterm;

import java.io.*;
import java.util.*;
public class M03_TopKConvenience {

    private static class Item {
        String name;
        long qty;
        Item(String name, long qty) { this.name = name; this.qty = qty; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = readNonEmpty(br);
        StringTokenizer st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // Min-Heap: 最差者在頂 (qty 小者更差；若 qty 同，name 大者更差)
        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return Long.compare(a.qty, b.qty); // qty asc
                return b.name.compareTo(a.name); // name desc (較大者更差)
            }
        });

        for (int i = 0; i < n; i++) {
            String line = readNonEmpty(br);
            String[] parts = line.split("\\s+"); // 題目保證品名不含空白
            String name = parts[0];
            long qty = Long.parseLong(parts[1]);

            Item cur = new Item(name, qty);
            if (pq.size() < K) {
                pq.offer(cur);
            } else {
                Item worst = pq.peek();
                if (isBetter(cur, worst)) { // 更好就替換堆頂
                    pq.poll();
                    pq.offer(cur);
                }
            }
        }

        // 取出並以最終輸出規則排序：qty 降序；qty 同時 name 升序
        List<Item> ans = new ArrayList<>(pq);
        ans.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return Long.compare(b.qty, a.qty); // qty desc
                return a.name.compareTo(b.name); // name asc
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Item it : ans) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }

    // 判斷 a 是否比 b 更好（排名應更前）
    private static boolean isBetter(Item a, Item b) {
        if (a.qty != b.qty) return a.qty > b.qty;                  // qty 大者更好
        return a.name.compareTo(b.name) < 0;                       // name 小者更好
    }

    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        throw new EOFException("Unexpected end of input");
    }
}