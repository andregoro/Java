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
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andregoro
 */
public class ClienteTeste {
 private static Socket soc;
    private static Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            System.out.println("Aguardando Conexão");
            soc = new Socket("localhost", 5001);
            System.out.println("Conexão bem Sucedida");
            
            DataOutputStream enviado = new DataOutputStream(soc.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    exec();
                }
            }).start();
            
            String input;
            
            while(true){
                input = scanner.nextLine();
                if(input.equals("-end")) break;
                enviado.writeUTF(input);
            }
            
        } catch (SocketException ex) {
        } catch (IOException ex) {
            Logger.getLogger(ClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(soc != null) try {
                soc.close();
            } catch (IOException ex) {
                System.out.println("Falha ao fechar o Socket");
            }
        }
    }
    
    private static void exec(){
        try {
            DataInputStream recebido = new DataInputStream(soc.getInputStream());
            
            while(true){
                System.out.println("(amigo): "+recebido.readUTF());
            }
            
        } catch (SocketException ex) {
            System.out.println("Fim da conexão");
        }  catch (EOFException ex) {
            System.out.println("Fim da conexão");
        } catch (IOException ex) {
            System.out.println("Falha na conexão");
            ex.printStackTrace();
        } finally{
            if(soc != null) try {
                soc.close();
            } catch (IOException ex) {
                System.out.println("Falha ao fechar o Socket");
            }
        }
    }

}
