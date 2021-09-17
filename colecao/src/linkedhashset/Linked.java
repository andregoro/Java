/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedhashset;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @author andregoro
 */
public class Linked {

    public Linked() {
        LinkedHashSet<Integer>sequencia=new LinkedHashSet<>();
        sequencia.add(1);
        sequencia.add(2);
        sequencia.add(4);
        sequencia.add(8);
        sequencia.add(16);
        System.out.println(sequencia);
        
        sequencia.remove(4);
        System.out.println(sequencia);
        
        System.out.println(sequencia.size());
        
        Iterator<Integer>interator=sequencia.iterator();
        
        while(interator.hasNext()){
            System.out.println(interator.next());
        }
        sequencia.clear();
    }
}
