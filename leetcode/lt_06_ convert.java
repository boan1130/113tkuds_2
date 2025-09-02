class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0;            // 目前所在列
        int dir = 1;          // 方向：1 往下，-1 往上（斜對角）
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            // 觸頂/觸底就折返
            if (r == 0) dir = 1;
            else if (r == numRows - 1) dir = -1;
            r += dir;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }
}
