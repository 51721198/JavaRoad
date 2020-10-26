package com.soapsnake.algorithms.leetcode.str;

/**
 *
 * Created on 2020-06-17
 */
public class Question640 {

    /**
     * Example 1:
     * Input: "x+5-3+x=6+x-2"
     * Output: "x=2"
     *
     * Example 2:
     * Input: "x=x"
     * Output: "Infinite solutions"
     *
     * Example 3:
     * Input: "2x=x"
     * Output: "x=0"
     *
     * Example 4:
     * Input: "2x+3x-6x=x+2"
     * Output: "x=-1"
     *
     * Example 5:
     * Input: "x=x+2"
     * Output: "No solution"
     */
    //leetcode640
    public String solveEquation(String equation) {
        int[] res = evaluateExpression(equation.split("=")[0]),
                res2 = evaluateExpression(equation.split("=")[1]);
        res[0] -= res2[0];
        res[1] = res2[1] - res[1];
        if (res[0] == 0 && res[1] == 0) return "Infinite solutions";
        if (res[0] == 0) return "No solution";
        return "x=" + res[1]/res[0];
    }

    public int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");
        int[] res =  new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }
}