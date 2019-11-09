import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

//Prefira implementar a interface Runnable do que extender a classe Thread, pois neste caso utilizaremos apena o m�todo run.
public class Cliente {

    public static void main(String[] args) 	{
        int cont=0;
        String str;
	char Espaco;
        try {
            Socket s = new Socket("127.0.0.1", 12345);
            InputStream i = s.getInputStream();
            OutputStream o = s.getOutputStream();
            do {
                System.out.println("---------------------------------");
                System.out.println("#######################");
                System.out.println("Opera��es B�sicas");
                System.out.println("#######################");
                System.out.println("");
                System.out.println("Adi��o:       1 + 1");
                System.out.println("Subtra��o:    1 - 1");
                System.out.println("Divis�o:      1 / 1");
                System.out.println("Mutiplica��o: 1 * 1");
                System.out.println("");
                System.out.println("#######################");
                System.out.println("Opera��es Complexas");
                System.out.println("#######################");
                System.out.println("");
                System.out.println("Raiz Quadrada:  R 1");
                System.out.println("Pot�ncia:       1 ^ 1");
                System.out.println("Porcentagem:    1 % 1");
                System.out.println("Fatorial:       1 !");
                System.out.println("---------------------------------");
                System.out.println("Digite a opera��o com espa�os entre os operadores: Ex(1 + 1)");
                byte[] line = new byte[100];
                System.in.read(line);
                o.write(line);
                i.read(line);
                str = new String(line);
     
                System.out.print("O resultado �: ");
                do{
                    
                    Espaco = str.charAt(cont);
                    
                    if ( (Character.isDigit(str.charAt(cont)) || str.charAt(cont) == '.') && str.charAt(cont) != '+' ){
                        System.out.print(str.charAt(cont));
                    }
                    if (Character.isLetter(str.charAt(cont))){
                    	System.out.print(str.charAt(cont));
                        if(str.charAt(cont+1) == ' '){
                            System.out.print(" ");
                            cont++; 
                        }
                    }
                    if(str.charAt(cont) == '!'){
                    	Espaco = ' ';
                    	System.out.print("!");
                    }
                    if(cont == 99)
                        break;
                    else
                        cont++;
                        
                }while(Espaco != ' ');
					
		System.out.println(" ");
		cont=0;
                
            } while ( !str.trim().equals("bye") );
            s.close();
        }
        catch (Exception err) {
            System.err.println(err);
        }
    }
}

