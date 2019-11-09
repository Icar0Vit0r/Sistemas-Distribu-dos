import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.String;
import java.lang.Character;
import java.util.Arrays;


public class SimpleJavaServer{
	
public static double calculadora(String teste){
	
    String v = teste.trim();
    String[] separador = v.split(" ");
    int k = 0, n2 = 0, erro = 0, qtd = separador.length ;	
    boolean entrou = false;
    double calc = 0,num1, num2;

// multiplicação
    while(k < qtd){
        if (separador[k].equals("*")){
            num1 = Double.valueOf(separador[k-1]);
            num2 = Double.valueOf(separador[k+1]);
            calc = num1 * num2; 
            separador[k] = ""+calc;
            separador[k-1] = ""+calc;
            separador[k+1] = ""+calc;
            entrou = true;
	}
	if(entrou == true){
            k = 0;
            n2=1;
            entrou = false;
        }else
            n2 =0;
        
        if (n2==0){
            k++;
	}
    } k = 0;
// Divisão
    while(k < qtd){
        if (separador[k].equals("/")){
            num1 = Double.valueOf(separador[k-1]);
            num2 = Double.valueOf(separador[k+1]);
        if(num2 == 0){
            erro = 1010109;
            break;
	}
	calc = num1 / num2; 
	separador[k] = ""+calc;
	separador[k-1] = ""+calc;
	separador[k+1] = ""+calc;
	entrou = true;
	}
	if(entrou == true){
            k = 0;
            n2=1;
            entrou = false;
	}else
            n2 =0;

	if (n2==0){
            k++;
	}
    }k = 0;
// Adição
    while(k < qtd){
        if (separador[k].equals("+")){
            num1 = Double.valueOf(separador[k-1]);
            num2 = Double.valueOf(separador[k+1]);
            calc = num1 + num2; 
            separador[k] = ""+calc;
            separador[k-1] = ""+calc;
            separador[k+1] = ""+calc;
            entrou = true;
        }
	if(entrou == true){
            k = 0;
            n2=1;
            entrou = false;
	}else
            n2 =0;

	if (n2==0){
            k++;
        }
    }k = 0;
// Subtração
    while(k < qtd){
        if (separador[k].equals("-")){
            num1 = Double.valueOf(separador[k-1]);
            num2 = Double.valueOf(separador[k+1]);
            calc = num1 - num2; 
            separador[k] = ""+calc;
            separador[k-1] = ""+calc;
            separador[k+1] = ""+calc;
            entrou = true;
	}
	if(entrou == true){
            k = 0;
            n2=1;
            entrou = false;
	}else
            n2 =0;

	if (n2==0){
            k++;
        }	
    }
    if(erro == 1010109)
        calc = erro;
	return calc;
}

    public static void main(String[] args) 	{
        try {
            ServerSocket s = new ServerSocket(9999);
            String str, sair = "sair";
            double Resultado;
 
            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    str = new String(line);
                      
					Resultado = calculadora(str);
					if(Resultado == 1010109){
						o.write("Nao e possivel Dividir por Zero!".getBytes());
					}else{
						str = new String(""+Resultado+" ");
						o.write(str.getBytes());
					}
                } while ( !sair.equals("sair") );
                if(str.trim().equals("bye")){
                    c.close();
                    break;
                }
            }
        }
        catch (Exception err){
            System.err.println(err);
        }
    }
}