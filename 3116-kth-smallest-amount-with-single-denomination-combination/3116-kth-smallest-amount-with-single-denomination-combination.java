class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        long high = minCoin * (long) k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMultiples(coins, mid) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long countMultiples(int[] coins, long x) {
        int n = coins.length;
        long total = 0;

     
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                 
                    if (currentLcm > x) break;
                }
            }

            if (currentLcm <= x) {
                long term = x / currentLcm;
                if (bitCount % 2 == 1) {
                    total += term;
                } else {
                    total -= term;
                }
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}