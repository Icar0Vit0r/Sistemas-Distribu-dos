/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ti
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class CalculadoraCliente { 
    public static void main(String[] args) throws RemoteException, NotBoundException  { 
        long x, y;
        String Op = "";
        Scanner read = new Scanner(System.in);
        
      
        while(!(Op.equals("S"))){
        	System.out.println("=============== Operacoes disponiveis ===============");
            System.out.println("Informe a opera��o ou digite S para sair:  ");
            System.out.println("[+] [-] [/] [*] [Pow (Potenciacao)] [Rad (Radiciacao)]");
            Op = read.next();
        	switch(Op){
            case "+":
                try{ 
                    System.out.println("=============== ADI��O ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server");
                    System.out.println("Informe o 1� n�mero:  ");
                    x = read.nextLong();
                    System.out.println("Informe o 2� n�mero:  ");
                    y = read.nextLong();
                    System.out.println("Adi��o : "+c.calculate(x, y, Op)); 
                }
                catch (Exception e){ 
                    System.out.println(e); 
                }  
                break;
            case "-":  
                try{ 
                    System.out.println("=============== SUBTRA��O ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server"); 
                    System.out.println("Informe o 1� n�mero:  ");
                    x = read.nextLong();
                    System.out.println("Informe o 2� n�mero:  ");
                    y = read.nextLong();
                    System.out.println("Subtra��o : "+c.calculate(x, y, Op)); 
                }
                    catch (Exception e){ 
                    System.out.println(e); 
                } 
                break;
            case "*":
                try{ 
                    System.out.println("=============== MULTIPLICA��O ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server"); 
                    System.out.println("Informe o 1� n�mero:  ");
                    x = read.nextLong();
                    System.out.println("Informe o 2� n�mero:  ");
                    y = read.nextLong();
                    System.out.println("Multiplica��o : "+c.calculate(x, y, Op)); 
                }
                    catch (Exception e){ 
                    System.out.println(e); 
                } 
                break;
            case "/":   
                 try{ 
               
                    System.out.println("=============== DIVIS�O ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server");
               
                    System.out.println("Informe o 1� n�mero dividendo:  ");
                    x = read.nextLong();
                    System.out.print("Informe o 2� n�mero divisor:  ");
                    y = read.nextLong();
                
                   while(y == 0) {
                     System.out.print("N�o existe divis�o por 0... informe outro n�mero.");
                     System.out.print("Informe o 2� n�mero divisor:  ");
                     y = read.nextLong();	
                   }
                       
                    System.out.println("Divis�o : "+c.calculate(x, y, Op)); 
                }
                    catch (Exception e){ 
                    System.out.println(e); 
                }
                break;
            case "Pow":
            	try {
            		System.out.println("=============== POTENCIA ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server");
                    System.out.println("Informe a base:  ");
                    x = read.nextLong();
                    System.out.println("Informe o expoente:  ");
                    y = read.nextLong();
                    System.out.println("Potencia: "+c.calculate(x, y, Op));
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            	break;
            case "Rad":
            	try {
            		System.out.println("=============== RADICIACAO ===============");
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    CalculadoraServerInt c = (CalculadoraServerInt) reg.lookup("Server");
                    System.out.println("Informe o radicando:  ");
                    x = read.nextLong();
                    System.out.println("Informe o radical:  ");
                    y = read.nextLong();
                    System.out.println("Radiciacao: "+c.calculate(x, y, Op));
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            	break;

        	}
            
        	
        }
			
        System.out.println("Saindo...");              
    }
}

