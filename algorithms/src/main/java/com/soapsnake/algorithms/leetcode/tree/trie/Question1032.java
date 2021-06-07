package com.soapsnake.algorithms.leetcode.tree.trie;

/**
 * 
 * Created on 2020-08-24
 */
public class Question1032 {

    class TrieNode {

        boolean isWord;
        TrieNode[] next;

        public TrieNode(){}

        public TrieNode(boolean isWord, TrieNode[] next) {
            this.isWord = isWord;
            this.next = next;
        }
    }


    class StreamChecker {

        TrieNode root = new TrieNode();
        StringBuilder sb = new StringBuilder();

        public StreamChecker(String[] words) {
            createTrie(words);
        }

        public boolean query(char letter) {
            sb.append(letter);
            TrieNode node = root;
            for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
                char c = sb.charAt(i);
                node = node.next[c - 'a'];
                if (node != null && node.isWord) {
                    return true;
                }
            }
            return false;
        }

        private void createTrie(String[] words) {
            for (String s : words) {
                TrieNode node = root;
                int len = s.length();
                for (int i = len - 1; i >= 0; i--) {
                    char c = s.charAt(i);
                    if (node.next[c - 'a'] == null) {
                        node.next[c - 'a'] = new TrieNode();
                    }
                    node = node.next[c - 'a'];
                }
                node.isWord = true;
            }
        }
    }
}
