package com.soapsnake.algorithms.leetcode.dp;

public class Question96 {


    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     * <p>
     * Example:
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     */
    public int numTrees(int n) {
        /**
         * The essential process is:to build a tree,we need to pick a root node, then we need to know how many
         * possible left sub trees and right sub trees can be held under that node, finally multiply them.
         * To build a tree contains{1, 2, 3, 4, 5}. First we pick 1 as root,for the left sub tree,there are none;
         * for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5},
         * apparently it's the same number as {1,2,3,4}.So the total number of trees under "1" picked as root is
         * dp[0] * dp[4] = 14.(assume dp[0]=1).Similarly,root 2 has dp[1] * dp[3] = 5 trees. root 3 has dp[2] * dp[2]
         * = 4, root 4 has dp[3]* dp[1] = 5 and root 5 has dp[0] * dp[4]=14.Finally sum the up and it's done.
         *
         * Now, we may have a better understanding of the dp[k], which essentially represents the number of BST trees
         * with k consecutive nodes.It is used as database when we need to know how many left sub trees are possible
         * for k nodes when picking(k+1) as root.
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int level = 2; level <= n; level++) {
            for (int root = 1; root <= level; root++) {
                dp[level] += dp[level - root] * dp[root - 1];
            }
        }
        return dp[n];
    }
}
