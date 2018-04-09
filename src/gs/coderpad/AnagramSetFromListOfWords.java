package gs.coderpad;

import java.util.*;

public class AnagramSetFromListOfWords {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("and");
        list.add("dna");
        list.add("dan");
        list.add("dog");
        list.add("god");
        list.add("banana");

        System.out.println(getAnagramSets(list));
    }

    public static Collection<List<String>> getAnagramSets(List<String> wordList){
        Map<String, List<String>> map = new HashMap<>();
        for(String word: wordList){
            char[] charArr = word.toCharArray();
            Arrays.sort(charArr);
            String key = String.valueOf(charArr);
            List<String> list = null;
            if(map.containsKey(key))
                list = map.get(key);
            else
                list = new ArrayList<>();
            list.add(word);
            map.put(key, list);
        }
        return map.values();
    }
}
