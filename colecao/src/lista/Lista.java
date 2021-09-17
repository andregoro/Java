/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author andregoro
 */

public class Lista {
    List<String>nome=new ArrayList<>();

    public Lista() {
        nome.add("andre");
        nome.add("pedro");
        nome.add("juliana");
        nome.add("joao");
        
        System.out.println(nome);
        
        nome.set(3,"ira");
        
        System.out.println(nome);
        
        Collections.sort(nome);
        
        System.out.println(nome);
    }
    
}
