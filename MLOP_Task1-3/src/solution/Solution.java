/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import solution.SearchEngine.*;
import solution.SearchEngine.Structures.*;

import java.util.*;
import java.io.*;


public class Solution{
    private Scanner scan = new Scanner(System.in);
    
    public void demoSorter() throws FileNotFoundException{
        CsvReader csv = new CsvReader(new FileInputStream(new File(scan.nextLine().trim())) );
        Vector<Student> stud = new Vector();
        int cols = csv.getNumCols();
        
        int i;
        for(i=0;i<cols;i++){
            stud.add(new Student(csv.get(i,0), Integer.parseInt(csv.get(i, 1))));
        }
        
        QuickSorter qs = new QuickSorter(stud);
        stud = qs.sort(new SortByMark());
        
        for(i=0;i<cols;i++){
            System.out.println(stud.get(i).getDescr());
        }
    }
    
    public void demoSnake(){
        int M = scan.nextInt();
        int N = scan.nextInt();
        int[][] array = SnakeGen.getMatrix(M, N);
       
        int i, j;
        for(i=1;i<=M;i++){
            String out = "";
            for(j=1;j<=N;j++)
                out += array[i][j] + " ";
            System.out.println(out);
        }
    }
    
    public void loop(){
        try{
            SearchEngine se = null;
            String query = "";
            String ots = "  ";
            
            while(!query.startsWith("exit")){
                System.out.print("> ");
                query = scan.next();
                if(query.equals("import")){
                    String dir = scan.next();
                    se = new SearchEngine(dir);
                    int numWords = se.getIndexer().getWfd().size();
                    System.out.format(ots + "Status: OK, %d words%n", numWords);
                }
                else if(query.equals("find")){
                    if(se != null){
                        String word = scan.next();
                        FD fd = se.getOrderedFD(word);

                        if(fd == null || fd.isEmpty()){
                            System.out.println(ots + "Word is not found");
                        }
                        else{
                            for(Map.Entry me : fd.entrySet()){
                                System.out.format(ots + "%s, %d%n", me.getValue(), me.getKey());
                            }
                        }
                    }
                    else{
                        System.out.println("Please import your documents");
                    }
                }
                else if(query.equals("snake")){
                    demoSnake();
                }
                else if(query.equals("sorter")){
                    demoSorter();
                }
                else if(query.equals("words")){
                    WFD wfd = se.getIndexer().getWfd();
                    for(Map.Entry me : wfd.entrySet()){
                        System.out.println(me.getKey());
                    }
                }
                else{
                    System.out.println("Commands: import find words exit snake sorter");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
     public static void main(String []args){
         Solution sol = new Solution();
         sol.loop();
     }
}
