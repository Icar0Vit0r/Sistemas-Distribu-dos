import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;

public class Server implements Runnable {
    private int porta;
    public Socket cliente;
    
    private static List<Integer> listaServer = new ArrayList<Integer>();
    private static List<Integer> listaServerPX = new ArrayList<Integer>();
    private static List<Socket> listaDeClientes = new ArrayList<Socket>();
    
    public Server(Socket cliente){
        this.cliente = cliente;
    }
   
    public static void main(String[] args)  throws IOException {
        listaServer.add(9999);
        listaServer.add(9998);
        listaServerPX.add(10000);
        listaServerPX.add(10001);

        //Cria um socket na porta 12345
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Porta 12345 aberta!");

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        System.out.println("Aguardando conexão do cliente...");

        while (true) {
            Socket cliente = servidor.accept();
            
            listaDeClientes.add(cliente);
            // Cria uma thread do servidor para tratar a conexão
            Server tratamento = new Server(cliente);
            Thread t = new Thread(tratamento);
            
            // Inicia a thread para o cliente conectado
            t.start();
        }
    }

    /* A classe Thread, que foi instancia no servidor, implementa Runnable.
       Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'.
    */
    public byte[] serve1(byte[] linha) throws IOException{
             
             Socket s = new Socket("127.0.0.1", porta);
             
                    InputStream entrada = s.getInputStream();
                    OutputStream saida = s.getOutputStream();
                    saida.write(linha);
                    entrada.read(linha); 
            return linha;
    }
    public byte[] serve2(byte[] linha) throws IOException{

             Socket s = new Socket("127.0.0.1", porta);
             
                    InputStream entrada = s.getInputStream();
                    OutputStream saida = s.getOutputStream();
                    saida.write(linha);
                    entrada.read(linha);          
            return linha;
    }
    public void posicao(){
        int volante=0, tamanho=listaServer.size();
        volante = listaServer.get(0);
        for(int i=0;i<tamanho;i++){      
            if((i+1) == tamanho){
                listaServer.set(i,volante);
            }else
                listaServer.set(i,listaServer.get(i+1));
        }
    }

    public void posicaoPX(){
        int volante=0, tamanho=listaServerPX.size();
        volante = listaServerPX.get(0);
        for(int i=0;i<tamanho;i++){
            if((i+1) == tamanho){
                listaServerPX.set(i,volante);
            }else
                listaServerPX.set(i,listaServerPX.get(i+1));
        }
    }
    
    public int escolha(byte[] linha){
        String aux;
        aux = new String(linha);
        String v = aux.trim();
        String[] separador = v.split(" ");
        int k=0, qtd = separador.length, opr=0;
       while(k < qtd){
           if(separador[k].equals("*") || separador[k].equals("/") || separador[k].equals("+") || separador[k].equals("-")){
                opr = 1;
                porta = listaServer.get(0);
		posicao();
           }else
                if(separador[k].equals("R") || separador[k].equals("%") || separador[k].equals("^") || separador[k].equals("!")){
                    opr = 2;
                    porta = listaServerPX.get(0);
                    posicaoPX();
                }
            k++;
        } 
       return opr;
    }
        
    public void run(){
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());
        String str;
        int opc=0;   
        try {
            InputStream i = this.cliente.getInputStream();
            OutputStream o = this.cliente.getOutputStream();
            
            do {
                byte[] line = new byte[100];
                i.read(line);
                str = new String(line);
	        opc = escolha(line);
                listaDeClientes.forEach(e -> {
                    try {
                       if(escolha(line) == 1){
                          o.write(serve1(line)); 
                       }else
                           if(escolha(line) == 2){
                              o.write(serve2(line)); 
                           }
                    posicao();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                
            } while (!str.trim().equals("bye"));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}