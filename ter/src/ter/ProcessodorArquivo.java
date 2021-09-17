/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Timer;


public class ProcessodorArquivo {
    
//    private Timer timer;
//    private ProcessodorArquivo tarefa;
//    private int pausa;
//
//    public ProcessodorArquivo() {
//        timer = new Timer();
//        pausa = 1000;
//        tarefa = new RepetidorTimerTask("C:\Users\´Gabriel\Desktop\arquivoTeste.txt");
//        timer.schedule(tarefa, 0);
//    }
//    
    void acrecentaFinal(File arq) throws FileNotFoundException, IOException{    

            RandomAccessFile raf = new RandomAccessFile(arq, "rw");
            raf.seek(raf.length());
            raf.writeBytes("OK");
            raf.close();
  }
    
    void alteraFinal(File arq){
      String data="";
      
        try {

      Scanner myReader = new Scanner(arq);
      while (myReader.hasNextLine()) {
       data=myReader.nextLine();
       // System.out.println(data);
      }
      myReader.close();
      FileWriter myWriter = new FileWriter(arq);
      myWriter.write(data.substring(0,data.length()-2)+"KO");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }
}
