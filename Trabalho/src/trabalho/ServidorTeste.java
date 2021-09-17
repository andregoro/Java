/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andregoro
 */
public class ServidorTeste {

private static ServerSocket svSoc;
    private static Socket soc;
    private static Socket soc2;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            try{
                svSoc = new ServerSocket(5001);
                System.out.println("Aguardando Conexão 1");
                soc = svSoc.accept();
                System.out.println("Conexão bem Sucedida 1");
                System.out.println("Aguardando Conexão 2");
                soc2 = svSoc.accept();
                System.out.println("Conexão bem Sucedida 2");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        exec(1,soc,soc2);
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        exec(2,soc2,soc);
                    }
                }).start();

            }catch(IOException io){
                System.out.println("Falha na conexão no lado do servidor");
            }finally{
                try{
                    if(svSoc != null) svSoc.close();
                }catch(IOException io){
                    System.out.println("Falha ao finalizar o Servidor");
                }
            }
        }
    }
    
    private static void exec(int id, Socket soc1, Socket soc2){
        try {
            DataInputStream recebido = new DataInputStream(soc1.getInputStream());
            DataOutputStream enviado = new DataOutputStream(soc2.getOutputStream());
            
            String input;
            
            while(true){
                input = recebido.readUTF();
                System.out.println("(cliente " + id + "): " + input);
                enviado.writeUTF(input);
            }
            
        } catch (SocketException ex) {
        } catch (EOFException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            if(soc1 != null) try {
                soc1.close();
            } catch (IOException ex) {
                System.out.println("Erro ao fechar Socket 1");
            }
            if(soc2 != null) try {
                soc2.close();
            } catch (IOException ex) {
                System.out.println("Erro ao fechar Socket 2");
            }
        }
    }
}
