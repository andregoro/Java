/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import servidorsocket.Cliente;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andregoro
 */
public class Distribuido_Sistema {

    private static ServerSocket svSoc;
    private static Socket soc;
    private static Socket soc2;
    private static List<Cliente> cliente = new ArrayList<>();
    private static List<String> nomeLista = new ArrayList<>();
    private static String nome = null;

    /**
     * @param args the command line arguments
     */
    public static boolean armazena(String newName) throws IOException {
        boolean bot = true;
        for (int i = 0; i < cliente.size(); i++) {
            if (cliente.get(i).nome.equals(newName)) {
                bot = false;
            }
        }
        //adiciona na lista apenas se não existir
        cliente.add(new Cliente(newName, soc, new PrintStream(soc.getOutputStream())));
        return bot;
    }

    //remove da lista os CLIENTES que já deixaram o chat
    public static void remove(String oldName) {
        for (int i = 0; i < cliente.size(); i++) {
            if (cliente.get(i).equals(oldName)) {
                cliente.remove(oldName);
            }
        }
    }

    public static void main(String[] args) {
        // cliente
        try {
            svSoc = new ServerSocket(5001);
            System.out.println("Aguardando Conexão ");// + Integer.toString(cliente.size() + 1));
            while (true) {
                soc = svSoc.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            publica();
                        } catch (IOException ex) {
                            Logger.getLogger(Distribuido_Sistema.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            //  publica();
//                            msg();
//                        } catch (IOException ex) {
//                            Logger.getLogger(Distribuido_Sistema.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }).start();
//            for (Cliente c : cliente) {
//                if (c.nome.equals(cliente.get(id).nome)) {
//                    continue;
//                }
//                DataInputStream recebido = new DataInputStream(c.soc.getInputStream());
//                DataOutputStream enviado = new DataOutputStream(c.soc.getOutputStream());
//                String n=recebido.readUTF();
//                System.out.println(n);
//            }
            }
        } catch (IOException io) {
            System.out.println("Falha ao finalizar o Servidor");
        } finally {
            try {
                if (svSoc != null) {
                    svSoc.close();
                }
            } catch (IOException io) {
                System.out.println("Falha ao finalizar o Servidor");
            }
        }

    }

    private static void enviar(int id) {
        try {
            DataInputStream recebido[] = new DataInputStream[cliente.size() - 1];

            DataOutputStream enviado = new DataOutputStream(cliente.get(id).soc.getOutputStream());
            for (int i = 0; i < cliente.size(); i++) {
                if (i != id) {
                    recebido[i] = new DataInputStream(cliente.get(i).soc.getInputStream());
                }
            }
            String input;

            while (true) {
                //input = recebido.readUTF();
                // System.out.println("(cliente " + id + "): " + input);
                for (int i = 0; i < cliente.size(); i++) {
                    if (i != id) {
                        input = recebido[i].readUTF();
                        enviado.writeUTF(cliente.get(i).nome + input);
                    }
                }
            }

        } catch (SocketException ex) {
        } catch (EOFException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cliente.get(id).soc != null) {
                try {
                    cliente.get(id).soc.close();
                } catch (IOException ex) {
                    System.out.println("Erro ao fechar Socket 1");
                }
            }
            if (soc2 != null) {
                try {
                    soc2.close();
                } catch (IOException ex) {
                    System.out.println("Erro ao fechar Socket 2");
                }
            }
        }
    }

    public static boolean msg(String nome) throws IOException {
        int id = 0;

        for (int i = 0; i < cliente.size(); i++) {
            if (cliente.get(i).nome.equals(nome)) {
                id = i;
                break;
            }
        }
        final int ids = id;
        while (true) {
            DataInputStream recebido2 = new DataInputStream(cliente.get(id).soc.getInputStream());
            DataOutputStream enviado2 = new DataOutputStream(cliente.get(id).soc.getOutputStream());
            String m = cliente.get(id).nome + ":" + recebido2.readUTF();
            System.out.println(m);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (cliente.size() > 2) {
                        exec(ids, cliente.get(ids).soc, cliente.get(0).soc);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (cliente.size() > 2) {
                        exec(0, cliente.get(0).soc, cliente.get(ids).soc);
                    }
                }
            }).start();

//
//            for (int i = 0; i < cliente.size(); i++) {;
//                if (cliente.get(i).nome.equals(nome)) {
//                    continue;
//                }
//            for (Cliente c : cliente) {
//                if (c.nome.equals(cliente.get(id).nome)) {
//                    continue;
//                }
//                DataInputStream recebido = new DataInputStream(c.soc.getInputStream());
//                DataOutputStream enviado = new DataOutputStream(c.soc.getOutputStream());
//                String n=recebido.readUTF();
//                System.out.println(n);
//            }
            //}
//            if (m.contains("-pr")) {
//                System.out.println("encotrou");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        exec(1, cliente.get(0).soc, cliente.get(1).soc);
//                    }
//                }).start();
//
//            }
        }
        //   return false;
    }

    private static void publica() throws IOException {
        DataInputStream recebido = new DataInputStream(soc.getInputStream());
        BufferedReader entrada = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        ;

        nome = recebido.readUTF();
        if (armazena(nome) == true) {
            System.out.println("Conexão bem Sucedida " + nome);// + Integer.toString(cliente.size() + 1));

            msg(nome);
            String mes = entrada.readLine();
            while (mes != null) {
                sendToAll(, " escreveu: ", msg);
                mes = entrada.readLine();
            }

        } else {
            soc.close();
            return;
        }
    }

    private static void exec(int id, Socket soc1, Socket soc2) {
        try {
            DataInputStream recebido = new DataInputStream(soc1.getInputStream());
            DataOutputStream enviado = new DataOutputStream(soc2.getOutputStream());

            String input;

            while (true) {
                input = recebido.readUTF();
                System.out.println("(cliente " + id + "): " + input);
                enviado.writeUTF(cliente.get(id).nome + input);
            }

        } catch (SocketException ex) {
        } catch (EOFException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (soc1 != null) {
                try {
                    soc1.close();
                } catch (IOException ex) {
                    System.out.println("Erro ao fechar Socket 1");
                }
            }
            if (soc2 != null) {
                try {
                    soc2.close();
                } catch (IOException ex) {
                    System.out.println("Erro ao fechar Socket 2");
                }
            }
        }
    }
}
