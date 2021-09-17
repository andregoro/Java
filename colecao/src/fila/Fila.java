/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fila;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author andregoro
 */
public class Fila {
    Queue<String> fila=new LinkedList<>();

    public Fila() {
        fila.add("andre");
        fila.add("ande");
        fila.add("log");
        fila.add("jojo");
        System.out.println(fila);
    }
}
