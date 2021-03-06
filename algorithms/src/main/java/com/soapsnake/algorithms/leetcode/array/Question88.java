package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-16 18:20
 */
public class Question88 {

    public static void main(String[] args) {
        Question88 question88 = new Question88();
        int[] nums1 = {2, 0};
        int m = 1;
        int[] nums2 = {1};
        int n = 1;
        System.out.println(Arrays.toString(question88.merge(nums1, m, nums2, n)));
    }

    //这个解法感觉特别像选择排序
    //todo
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int f = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[f] = nums1[i];
                i--;
            } else {
                nums1[f] = nums2[j];
                j--;
            }
            f--;
        }
        while (j >= 0) {
            nums1[f--] = nums2[j--];  //这个其实没有看懂!!!
        }
        return nums1;
    }
}
