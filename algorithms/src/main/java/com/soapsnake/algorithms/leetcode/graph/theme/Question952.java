package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.HashMap;
import java.util.Map;

import com.soapsnake.algorithms.structures.unionfind.DSU;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-08-30
 */
public class Question952 {

    /**
     * Union Find template. The only additional stuff is one hashmap which is used to convert factor to the node
     * index in A for union.
     * HashMap: key is the factor, val is the index in A
     * Time complexity: O(N*sqrt(Max val of A[i]))
     */
    public int largestComponentSize(int[] A) {
        int N = A.length;
        Map<Integer, Integer> map = new HashMap<>();// key is the factor, val is the node index
        DSU uf = new DSU(N);
        for (int i = 0; i < N; i++){
            int a = A[i];
            for (int j = 2; j * j <= a; j++){
                if (a % j == 0){
                    if (!map.containsKey(j)){//this means that no index has claimed the factor yet
                        map.put(j, i);
                    }else{//this means that one index already claimed, so union that one with current
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(a/j)){
                        map.put(a/j, i);
                    }else{
                        uf.union(i, map.get(a/j));
                    }
                }
            }
            if (!map.containsKey(a)){//a could be factor too. Don't miss this
                map.put(a, i);
            }else{
                uf.union(i, map.get(a));
            }
        }
        return uf.getMax();
    }
}
