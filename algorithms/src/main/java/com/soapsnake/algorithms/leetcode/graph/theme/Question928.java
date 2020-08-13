package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-08-11
 */
public class Question928 {

    //leetcode928
    public int minMalwareSpread(int[][] graph, int[] initial) {
        Arrays.sort(initial);
        Set<Integer> mal = new HashSet<>();
        for(int n : initial) {
            mal.add(n);
        }

        int max = -1, ret = -1;
        for(int n : initial){
            int save = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(n);
            for(int i = 0; i<graph.length; i++){
                if(i != n && graph[n][i] == 1){
                    int temp = dfs(i, visited, mal, graph);
                    if(temp < 0) continue; // encountered malware during exploration, meaning this whole branch doesn't count/contribute
                    save += temp;
                }
            }
            if(save > max){
                ret = n;
                max = save;
            }
        }
        return ret;
    }

    private int dfs(int n, Set<Integer> visited, Set<Integer> mal, int[][] graph){
        if(visited.contains(n)) return 0;
        if(mal.contains(n)) return -1;
        visited.add(n);

        int ret = 1; // current node saved (at least for now)
        for(int i = 0; i<graph.length; i++){
            if(i != n && graph[n][i] == 1){
                int temp = dfs(i, visited, mal, graph);
                if(temp == -1) {
                    mal.add(n); // has neighbor malware, marked as malware as well
                    return -1; // return -1, indicating there's malware downstream in this branch, whole branch unqualified!
                }
                ret += temp;
            }
        }
        return ret;
    }
}
