/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.lang.*; 

class vec2d{
    public int x;
    public int y;
    
    vec2d(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class SnakeGen{
    private vec2d cur = new vec2d(0, 1);
    private int index = 0;
    private int n, m;
    
    SnakeGen(int m, int n){
        this.n = n;
        this.m = m;
    }
    
    public boolean hasNext(){
        if(index>=m*n)
            return false;
        else
            return true;
    }
    
    public vec2d next(){
        int x = cur.x-1;
        int y =cur.y-1;
        int ix = n-cur.x;
        int iy = m-cur.y;
        int min = Math.min(Math.min(x, y), Math.min(ix, iy));
        
        if(min == x && x!= y &&  x!= y-1 && min != ix){
            cur.y--;
        }
        else if(min == iy && min != y){
            cur.x--;
        }
        else if(min == ix){
            cur.y++;
        }
        else if(min == y || x == y-1){
            cur.x++;
        }
        index++;
        
        return cur;
    }
    
    public int getIndex(){
        return index;
    }
}

class Task2{
    private static Scanner scan = new Scanner(System.in);
    public void doJob(){
        int M = scan.nextInt();
        int N = scan.nextInt();
        int[][] array = new int[M+1][N+1];
        SnakeGen snake = new SnakeGen(M, N);
         
        while(snake.hasNext()){
            vec2d p = snake.next();
            array[p.y][p.x] = snake.getIndex();
        }
         
        int i, j;
        for(i=1;i<=M;i++){
            String out = "";
            for(j=1;j<=N;j++)
                out += array[i][j] + " ";
            System.out.println(out);
        }
         
    }
}

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

class QuickSorter{
    private Vector arr;
    
    private void quick_sort(Comparator cmp, int b, int e){
    	int l = b, r = e;
    	Object piv = arr.get((l + r) / 2);
    	while (l <= r) {
    		while (cmp.compare(arr.get(l), piv) <0)
    			l++;
    		while (cmp.compare(piv , arr.get(r)) < 0)
    			r--;
    		if (l <= r){
    		    Object tmp = arr.get(l);
    		    arr.set(l, arr.get(r));
    		    arr.set(r, tmp);
    		    l++;
    		    r--;
    		}
    	}
    	if (b < r)
    		quick_sort(cmp, b, r);
    	if (e > l)
    		quick_sort(cmp, l, e);
    }
    
    QuickSorter(Vector obj){
        this.arr = new Vector(obj);
    }
    
    public Vector sort(Comparator cmp){
        quick_sort(cmp, 0, arr.size()-1);
        return arr;
    }
    
}

class Student{
    public int mark;
    public String last;
    
    Student(String last, int mark){
        this.last = last;
        this.mark = mark;
    }
    String getDescr(){
        return "Last: " + last + ", mark: " + mark;
    }
}


class SortByMark implements Comparator<Student>{
    public int compare(Student a, Student b) { 
        return a.mark - b.mark; 
    } 
}

class Task1{
    private static Scanner scan = new Scanner(System.in);
    public void doJob()throws FileNotFoundException{
        CsvReader csv = new CsvReader(new FileInputStream(new File(scan.nextLine())) );
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
}

class Document{
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


class DS extends ArrayList<String> {}
class FD extends TreeMap<Integer, DS> {
    FD(){
        super(Collections.reverseOrder());
    }
}
class WFD extends TreeMap<String, FD> {}


class Indexer implements Serializable{
    Vector<Document> doc = new Vector<Document>();
    WFD wfd= new WFD(); // word, doc.getName(), 
    
    public void add(Document d){
        doc.add(d);
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
    
    
    Indexer(InputStream is) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(is);
        this.wfd = (WFD) ois.readObject();
    }
    
    public void serialize(OutputStream os) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.wfd);
        oos.close();
    }
    
}

class SearchEngine{
    private File dir;
    private File wdf;
    private static String ext = "txt";
    private Indexer ind;
    
    SearchEngine(String path) throws Exception {
       dir = new File(path);
       if(!dir.isDirectory())
            throw new Exception("SearchEngine: enter a directory");
        
       wdf = new File(dir + "/wdf.dat");
       if(wdf.exists()){
           ind = new Indexer(new FileInputStream(wdf));
       }
       else{
            File[] fDocArray = dir.listFiles(new DocFilter(ext));
            ind = new Indexer();
            for(File fdoc : fDocArray){
                String content = getFileContent(new FileInputStream(fdoc));
                System.out.println(fdoc.getName());
                Document d = new Document(fdoc.getName(), content);
                ind.add(d);
            }
            ind.serialize(new FileOutputStream(wdf));
       }
    }
   
    public String getFileContent( FileInputStream fis ) throws IOException  {
        BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
        String out = "";
        
        while(bf.ready()){
            out += "\n" + bf.readLine();;
        }
        return out;
    }
    
    public FD getOrderedFD(String word){
        FD fd = ind.getWfd().get(word);
        if(fd == null)
            return null;
        
        FD cfd = new FD();
        cfd.putAll(fd);
        return cfd;
    }
    
    public Indexer getIndexer(){
        return ind;
    }
    
    	public static class DocFilter implements FilenameFilter {
		private String ext;

		public DocFilter(String ext) {
			this.ext = ext.toLowerCase();
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(ext);
		}
	}
}

class Task3{
    private static Scanner scan = new Scanner(System.in);
    public void doJob(){
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
                else if(query.equals("task2")){
                    Task2 task = new Task2();
                    task.doJob();
                }
                else if(query.equals("task1")){
                    Task1 task = new Task1();
                    task.doJob();
                }
                else if(query.equals("words")){
                    WFD wfd = se.getIndexer().getWfd();
                    for(Map.Entry me : wfd.entrySet()){
                        System.out.println(me.getKey());
                    }
                }
                else{
                    System.out.println("Commands: import find words exit task1 task2");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}

public class Solution{
     public static void main(String []args){
         Task3 task = new Task3();
         task.doJob();
         
        //Task2 task = new Task2();
        //task.doJob();
         
     }
}
