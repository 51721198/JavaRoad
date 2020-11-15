package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    //    private List<String> queue;
//    private int maxSize;
    public Solution() {
//        this.queue = new ArrayList<>();
//        this.maxSize = capacity;
    }

    public String[] solution(int capacity, String[] commands) {
        List<String> queue = new ArrayList<>(capacity);
        int curSize = 0;
        int maxSize = capacity;
        String[] res = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            String[] item = commands[i].split(" ");
            if (item.length >= 2) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("OFFER")) {
                    if (curSize + 1 > maxSize) {
                        res[i] = "false";
                    } else {
                        //in case the string contains space character,we should take all of these...
                        queue.add(commands[i].substring(realCommands.length() + 1));
                        curSize++;
                        res[i] = "true";
                    }
                }
            } else if (item.length == 1) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("TAKE")) {
                    if (curSize > 0) {
                        String dest = queue.remove(0);
                        curSize--;
                        res[i] = dest;
                    } else {
                        //if queue is empty,return nothing
                        res[i] = null;
                    }
                } else if (realCommands.equalsIgnoreCase("SIZE")) {
                    res[i] = String.valueOf(curSize);
                }
            }
        }
        return res;
    }

    public String[] solutionOld(int capacity, String[] commands) {
        String[] queue = new String[100000];
        int left = 0;
        int right = 0;
        int curSize = 0;
        int maxSize = capacity;
        String[] res = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            String[] item = commands[i].split(" ");
            if (item.length >= 2) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("OFFER")) {
                    if (curSize + 1 > maxSize) {
                        res[i] = "false";
                    } else {
                        //in case the string contains space character,we should take all of these...
                        queue[right++] = commands[i].substring(realCommands.length() + 1);
                        curSize++;
                        res[i] = "true";
                    }
                }
            } else if (item.length == 1){
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("TAKE")) {
                    if (curSize > 0) {
                        String dest = queue[left++];
                        curSize--;
                        res[i] = dest;
                    } else {
                        //if queue is empty,return nothing
                        res[i] = null;
                    }
                } else if (realCommands.equalsIgnoreCase("SIZE")) {
                    res[i] = String.valueOf(curSize);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(3, new String[]{"OFFER wo cao", "OFFER world", "OFFER !", "OFFER nim adf","TAKE", "TAKE", "SIZE", "TAKE", "SIZE", "WHAT"})));

    }

    //
    public int[] solution(int windowSize, int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return numbers;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[numbers.length - windowSize + 1];
        for (int i = 0; i < numbers.length; i++) {
            while (!queue.isEmpty() && numbers[queue.peekLast()] <= numbers[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - windowSize) {
                queue.poll();
            }
            if (i + 1 >= windowSize) {
                res[i + 1 - windowSize] = numbers[queue.peek()];
            }
        }
        return res;
    }


    /**
     * 寻找数组中重复和缺少的数字
     *
     * @return
     */
    public static int[] findDupAndLose(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] res = new int[2];
        Arrays.sort(arr);
        int dupIndex = -1;
        int dup = 0, lose = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                dup = arr[i];  //发现了重复数
                if (dupIndex != -1) {
                    throw new RuntimeException("多与两个数字重复");
                }
                dupIndex = i;
            } else if (arr[i] != (arr[i - 1] + 1)) {
                lose = arr[i - 1] + 1;
            }
        }
        if (dupIndex != -1) {
            res[0] = dup;
            res[1] = lose;
            return res;
        } else {
            return null;
        }
    }

    public int[] solution(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("illegal arguments!");
        }
        Arrays.sort(numbers);
        //we can use two pointers imply two sticks
        int left = 0;
        int right = numbers.length - 1;
        while (right > left) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{numbers[left], numbers[right]};
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        //if we couldn't find those two sticks, return an empty array
        return new int[0];
    }

}
