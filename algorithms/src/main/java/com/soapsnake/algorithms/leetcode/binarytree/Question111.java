package com.soapsnake.algorithms.leetcode.binarytree;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-08 20:35
 */
public class Question111 {

    private int mini = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        this.treeDfs(root, 0);
        return mini;
    }

    private void treeDfs(TreeNode root, int depth) {
        depth++;
        if (root.left == null && root.right == null) {
            this.mini = Math.min(depth, mini);
            return;
        }
        if (root.left != null) {
            treeDfs(root.left, depth);
        }

        if (root.right != null) {
            treeDfs(root.right, depth);
        }
    }

    public static void main(String[] args) {
        Question111 question111 = new Question111();

        TreeNode root = TreeNode.makeNormalTreeFor110();
        System.out.println(question111.minDepth(root));
    }
}