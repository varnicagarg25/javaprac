class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;
        
        int sumL = 0, sumR = 0;
        int qL = 0, qR = 0;
        
        for (int i = 0; i < mid; i++) {
            char c = num.charAt(i);
            if (c == '?') qL++;
            else sumL += c - '0';
        }
        
        for (int i = mid; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') qR++;
            else sumR += c - '0';
        }
        
       
        return 2 * (sumL - sumR) != (qR - qL) * 9;
    }
}