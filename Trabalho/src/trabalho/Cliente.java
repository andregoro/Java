/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.io.File;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author andregoro
 */
public class Cliente {

    String nome;
    Socket soc;
    File arquivo;
    PrintStream saida;

    public Cliente(String nome, Socket soc, PrintStream saida) {
        this.nome = nome;
        this.soc = soc;
        this.saida = saida;
    }

}
