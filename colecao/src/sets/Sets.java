/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author andregoro
 */
public class Sets {

    public Sets() {
        Set<Double>stes=new HashSet<>();
        stes.add(12.3);
        stes.add(1.2);
        stes.add(1.4);
        stes.add(13.0);
        
        System.out.println(stes);
        
        stes.remove(1.2);
        
        System.out.println(stes);
        
        System.out.println(stes.size());
        Iterator<Double> iterator=stes.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for(Double nota:stes){
            System.out.println(nota);
        }
        stes.clear();
    }
}
