/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vegeno-pc
 */
class CsvReader{
    private Vector<Vector<String>> items = new Vector();
    private int i = 0;
    private int j = 0;
    
    CsvReader(InputStream stream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); 
        
        Pattern p = Pattern.compile("[^,\"]+");
        String line;
        
        try{
            while((line = reader.readLine()) != null){
                Vector<String> cur = new Vector();
                Matcher m = p.matcher(line);
                while(m.find()){
                    cur.add(m.group());
                }
                items.add(cur);
                i++;
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    String get(int i, int j){
        return items.get(i).get(j);
    }
    
    int getNumCols(){
        return i;
    }
}