package com.ria.gradle.algorithms.string;

/**
 * 字符串匹配 - 暴力匹配算法
 * 时间复杂度O(m*n)
 */
public class BruteForceMatch {

    public static int find(String str, String substring) {
        int sLen = str.length(), subLen = substring.length();

        if (sLen <= 0 || sLen < subLen) {
            return -1;
        }

        for (int i=0; i < sLen - subLen + 1; ++i) {
            for (int j=0; j < subLen; ++j) {
                if (str.charAt(i+j) == substring.charAt(j)) {
                    if (j == subLen - 1) {
                        return i;
                    }
                } else {
                    break;
                }
            }
        }

        return -1;
    }
}

class BruteForceMatchTest {
    public static void main(String[] args) {
        System.out.println(BruteForceMatch.find("abcdefg", "cde"));
        System.out.println(BruteForceMatch.find("abcdefg", "dfds"));
    }
}
