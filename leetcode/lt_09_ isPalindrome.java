class Solution {
    public boolean isPalindrome(int x) {
        // 負數 or 最後一位是 0 (但 x != 0) 一定不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;
        // 只反轉一半，避免溢位
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // 若長度是偶數，x == reversed
        // 若長度是奇數，中間數要捨棄 (reversed/10)
        return x == reversed || x == reversed / 10;
    }
}
