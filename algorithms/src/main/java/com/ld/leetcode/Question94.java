package com.ld.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 二叉树的中序遍历
 *
 * *       3
 *        /\
 *      9   20
 *         / \
 *      15    7
 *          /  \
 *        8     12
 *      / \     / \
 *    6   9   10   22
 *           /  \
 *         4     14
 */
public class Question94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(cur!=null || !stack.empty()){
            while(cur!=null){
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }

    public static void main(String[] args) {

        Question94 question94 = new Question94();
        TreeNode node = TreeNode.makeTree();

       List<Integer> res =  question94.inorderTraversal(node);
        System.out.println(res);

    }
}

