/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author vegeno-pc
 */

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
