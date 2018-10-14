/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.SearchEngine;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vegeno-pc
 */
public class Document{
    private String content;
    private String name;
    private Map<String, Integer> freq = new TreeMap<String, Integer>();
    
    Document(String name, String content){
        this.content = content;
        this.name = name;
        Pattern p = Pattern.compile("\\b(\\w+)\\b");
        Matcher m = p.matcher(content);
        while(m.find()){
            String word = m.group(1).toLowerCase();
            if(freq.containsKey(word)){
                freq.put(word, freq.get(word)+1);
            }
            else{
                freq.put(word, 1);
            }
        }
    }
    
    public Map<String, Integer> getFreq(){
        return new TreeMap<String, Integer>(freq);
    }
    
    public String getName(){
        return name;
    }
}