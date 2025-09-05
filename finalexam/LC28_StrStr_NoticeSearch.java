package finalexam;

// 檔名：LC28_StrStr_NoticeSearch.java

import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String haystack = br.readLine();
        String needle = br.readLine();

        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        int index = strStr(haystack, needle);
        System.out.println(index);
    }

    // 暴力比對
    static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length()) return -1;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) return i; // 找到完整匹配
        }
        return -1;
    }
}
