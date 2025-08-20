package midterm;

import java.io.*;
public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 清洗：只保留英文字母，轉小寫
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        String cleaned = sb.toString();

        // 雙指標檢查回文
        int l = 0, r = cleaned.length() - 1;
        boolean ok = true;
        while (l < r) {
            if (cleaned.charAt(l) != cleaned.charAt(r)) {
                ok = false;
                break;
            }
            l++;
            r--;
        }

        System.out.println(ok ? "Yes" : "No");
    }
}