/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author andregoro
 */
public class Ter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      //  File f=new File("new.txt");
        File myObj = new File("C:\\Users\\andregoro\\Documents\\GitHub\\Java\\ter\\src\\ter\\new.txt");
        ProcessodorArquivo a = new ProcessodorArquivo();
        
        a.acrecentaFinal(myObj);
        
        a.alteraFinal(myObj);

    } 
    
}
