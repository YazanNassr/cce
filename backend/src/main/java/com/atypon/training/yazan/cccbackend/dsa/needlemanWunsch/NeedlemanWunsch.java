package com.atypon.training.yazan.cccbackend.dsa.needlemanWunsch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NeedlemanWunsch {
    private static final String firstSeparator
            = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    private static final String secondSeparator
            = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

    public String mergeStrings(String first, String second) {
        var firstElements = elements(first);
        var secondElements = elements(second);
        var result = mergeLists(firstElements, secondElements);

        return joinElements(result);
    }

    private String[] mergeLists(final String[] firstArray, final String[] secondArray) {
        int n = firstArray.length;
        int m = secondArray.length;

        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = -i;
        }

        for (int i = 1; i <= m; i++) {
            dp[0][i] = -i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) - 1;

                if (equalElements(firstArray[i-1], secondArray[j-1])) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }

        List<String> result = new ArrayList<>();

        int i = n, j = m;
        while (i > 0 || j > 0) {

            if (i > 0 && j > 0) {
                if (equalElements(firstArray[i-1], secondArray[j-1])) {
                    --i; --j;
                    result.add(firstArray[i]);
                } else if (dp[i-1][j] > dp[i][j-1]) {
                    --i;
                    result.add(firstSeparator);
                    result.add(firstArray[i]);
                    result.add(firstSeparator);
                } else {
                    --j;
                    result.add(secondSeparator);
                    result.add(secondArray[j]);
                    result.add(secondSeparator);
                }
            } else {
                if (i > 0) {
                    --i;
                    result.add(firstSeparator);
                    result.add(firstArray[i]);
                    result.add(firstSeparator);
                } else {
                    --j;
                    result.add(secondSeparator);
                    result.add(secondArray[j]);
                    result.add(secondSeparator);
                }
            }
        }

        LinkedList<String> res = new LinkedList<>();

        for (i = 0; i+1 < result.size(); ++i) {

            if (result.get(i).equals(result.get(i+1)) &&
                    (result.get(i).equals(firstSeparator) ||
                            result.get(i).equals(secondSeparator))) {
                i++;
            } else {
                res.addFirst(result.get(i));
            }

        }

        res.addFirst(result.getLast());

        return res.toArray(new String[0]);
    }

    private boolean equalElements(final String s1, final String s2) {
        return s1.hashCode() == s2.hashCode() && s1.equals(s2);
    }
    private String[] elements(final String s) {
        return s.split("\n");
    }
    private String joinElements(final String[] s) {
        return String.join("\n", s);
    }
}
