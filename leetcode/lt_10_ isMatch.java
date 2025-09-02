class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字串和空模式匹配
        dp[0][0] = true;

        // 初始化：處理像 a*, a*b*, a*b*c* 這種情況
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填 DP 表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '.') {
                    // 普通匹配或 '.'
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // '*' 可視為零次 (刪掉前一個字符和自己)
                    dp[i][j] = dp[i][j - 2];

                    // 或者如果前一個模式字符能匹配當前字符，就讓 '*' 再多吃一個字符
                    char prev = p.charAt(j - 2);
                    if (prev == sc || prev == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
