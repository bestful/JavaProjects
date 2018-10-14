/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.SearchEngine;

import solution.SearchEngine.Structures.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author vegeno-pc
 */

public class SearchEngine{
    public File dir;
    private File wdf;
    private static String ext = "txt";
    private Indexer ind;
    
    public SearchEngine(String path) throws Exception {
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
   
    private String getFileContent( FileInputStream fis ) throws IOException  {
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