/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.io.File;
import java.util.Timer;

/**
 *
 * @author andregoro
 */
public class Threads {

    private Timer timer;
    private Threads tarefa;
    private int pausa;

    public Threads(File arq) {
        timer = new Timer();
        pausa = 1000;
        tarefa = new Threads(arq);
        timer.schedule(tarefa, 0);
    }
}
