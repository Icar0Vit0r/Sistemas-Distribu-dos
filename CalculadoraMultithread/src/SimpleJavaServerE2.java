import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.String;
import java.lang.Character;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Arrays;


public class SimpleJavaServerE2 {
    
public static double calculadora(String teste){
	
    String v = teste.trim();
    String[] sep = v.split(" ");
    int k = 0, n2 = 0, erro = 0, qtd = sep.length ;	
    boolean entrou = false;
    double calc = 0,num1, num2;
// multiplicação
    while(k < qtd){
        if (sep[k].equals("R")){
            num2 = Double.valueOf(sep[k+1]);
            calc = sqrt(num2); 
            sep[k] = ""+calc;
            sep[k+1] = ""+calc;
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
        if (sep[k].equals("^")){
            num1 = Double.valueOf(sep[k-1]);
            num2 = Double.valueOf(sep[k+1]);

	calc = pow(num1, num2); 
	sep[k] = ""+calc;
	sep[k-1] = ""+calc;
	sep[k+1] = ""+calc;
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
        if (sep[k].equals("%")){
            num1 = Double.valueOf(sep[k-1]);
            num2 = Double.valueOf(sep[k+1]);
            calc = (num1 * num2) / 100; 
            sep[k] = ""+calc;
            sep[k-1] = ""+calc;
            sep[k+1] = ""+calc;
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
        if (sep[k].equals("!")){
            num1 = Double.valueOf(sep[k-1]);
            for(int i=0; i < num1;i++){
                 if (calc == 0)
                    calc += i+1;
                else
                    calc *= (i+1);
            }
            sep[k] = ""+calc;
            sep[k-1] = ""+calc;
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
            ServerSocket s = new ServerSocket(10001);
            String ret, str, sair = "sair";
            int calc;
            double hi;
 
            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    str = new String(line);  
					hi = calculadora(str);
					if(hi == 1010109){
						o.write("Nao e possivel Dividir por Zero!".getBytes());
					}else{
						str = new String(""+hi+" ");
						o.write(str.getBytes());
                                                System.out.println("10001");
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
