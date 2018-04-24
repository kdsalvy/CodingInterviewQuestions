package gs.interview;

import java.util.HashMap;
import java.util.Map;

public class TrieDS {

    TrieNode root;

    public void insertWord(String word){
        TrieNode temp = root;
        insertHelper(word,0,temp);
    }

    private void insertHelper(String word, int index, TrieNode node){
        if(index >= word.length()){
            return;
        }
        boolean eow = index == word.length() - 1? true : false;
        char c = word.charAt(index);
        TrieNode next = null;
        if(node.child.containsKey(c)){
            next = node.child.get(c);
        } else {
            next = new TrieNode(eow);
            node.child.put(c, next);
        }
        insertHelper(word, index + 1, next);
    }

    public boolean search(String word){
        TrieNode temp = root;
        return searchHelper(word,0,temp);
    }

    private boolean searchHelper(String word, int index, TrieNode node){
        if(index >= word.length())
            return true && node.endOfWord;
        char c = word.charAt(index);
        if(node.child.containsKey(c))
            node = node.child.get(c);
        else
            return false;
        return searchHelper(word, index + 1, node);
    }
}

class TrieNode{
    Map<Character, TrieNode> child;
    boolean endOfWord;

    public TrieNode(boolean eow){
        child = new HashMap<>();
        endOfWord = eow;
    }
}
