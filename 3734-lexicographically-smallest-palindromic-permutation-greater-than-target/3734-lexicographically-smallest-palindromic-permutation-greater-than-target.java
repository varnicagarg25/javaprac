class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int halfLen = n / 2;
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Count odd frequency characters
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // A valid palindrome must have at most 1 odd character count
        if (oddCount > 1) {
            return "";
        }

        // Frequency array representing the available characters for the first half
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Try to match prefix of length `i` with `target`, then make character at index `i` strictly larger
        for (int i = halfLen; i >= 0; i--) {
            // Check if halfFreq can match target[0 ... i-1]
            int[] currentFreq = halfFreq.clone();
            boolean canMatchPrefix = true;

            for (int k = 0; k < i; k++) {
                char tChar = target.charAt(k);
                if (currentFreq[tChar - 'a'] > 0) {
                    currentFreq[tChar - 'a']--;
                } else {
                    canMatchPrefix = false;
                    break;
                }
            }

            if (!canMatchPrefix) continue;

            // Case 1: The prefix matches completely up to halfLen.
            if (i == halfLen) {
                StringBuilder firstHalf = new StringBuilder(target.substring(0, halfLen));
                StringBuilder fullPal = new StringBuilder(firstHalf);
                if (n % 2 != 0) {
                    fullPal.append(midChar);
                }
                fullPal.append(new StringBuilder(firstHalf).reverse());

                if (fullPal.toString().compareTo(target) > 0) {
                    return fullPal.toString();
                }
                continue;
            }

            // Case 2: Make character at index `i` strictly greater than target[i]
            char minChar = (char) (target.charAt(i) + 1);
            for (char ch = minChar; ch <= 'z'; ch++) {
                if (currentFreq[ch - 'a'] > 0) {
                    currentFreq[ch - 'a']--;

                    StringBuilder resultHalf = new StringBuilder(target.substring(0, i));
                    resultHalf.append(ch);

                    // Fill remaining slots of the first half with smallest available characters
                    for (int c = 0; c < 26; c++) {
                        while (currentFreq[c] > 0) {
                            resultHalf.append((char) ('a' + c));
                            currentFreq[c]--;
                        }
                    }

                    // Construct the full palindromic string
                    StringBuilder fullPalindrome = new StringBuilder(resultHalf);
                    if (n % 2 != 0) {
                        fullPalindrome.append(midChar);
                    }
                    fullPalindrome.append(new StringBuilder(resultHalf).reverse());

                    return fullPalindrome.toString();
                }
            }
        }

        return "";
    }
}

