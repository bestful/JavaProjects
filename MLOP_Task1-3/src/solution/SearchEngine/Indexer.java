/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.SearchEngine;

import solution.SearchEngine.Structures.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author vegeno-pc
 */

public class Indexer implements Serializable{
    TreeMap<String, Document> doc = new TreeMap<String, Document>();
    WFD wfd= new WFD(); // word, doc.getName(), 
    
    public Document getDocument(String name){
        return doc.get(name);
    }
    
    public void add(Document d){
        doc.put(d.getName(), d);
        String name = d.getName();
        
        for(Map.Entry<String, Integer> me : d.getFreq().entrySet()){
            String word = me.getKey();
            int freq = me.getValue();
            if(wfd.containsKey(word)){
                FD fd = wfd.get(word);
                DS ds = fd.get(freq);
                if(ds == null){
                    ds = new DS();
                    ds.add(new String(name));
                    fd.put(freq, ds);
                }
                else{
                    ds.add(new String(name));
                }
            }
            else{
                FD fd = new FD();
                DS ds = new DS();
                ds.add(new String(name));
                fd.put(freq, ds);
                wfd.put(word, fd);
            }
        }
            
    }
    
    Indexer(){
        
    }
    
    public WFD getWfd(){
        return wfd;
    }
    
    
    public Indexer(InputStream is) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(is);
        this.wfd = (WFD) ois.readObject();
    }
    
    public void serialize(OutputStream os) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.wfd);
        oos.close();
    }   
}