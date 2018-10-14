/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution.SearchEngine.Structures;

import java.util.Collections;
import java.util.TreeMap;

/**
 *
 * @author vegeno-pc
 */

// Structure: map of frequency-documents
public class FD extends TreeMap<Integer, DS> {
    public FD(){
        super(Collections.reverseOrder());
    }
}