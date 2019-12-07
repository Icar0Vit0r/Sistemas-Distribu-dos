/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ti
 */
 public class CalculadoraEscravaImpl extends UnicastRemoteObject implements CalculadoraEscravaInt{
	/**
	 * 
	 */
	
	public long power(long base, long exp) {
		if(exp == 0) 
			return 1;
		else
			return base*power(base,exp - 1);
	}
	private static final long serialVersionUID = 1L;
	private String name;

	public CalculadoraEscravaImpl() throws RemoteException{
		super();	
	}

	public long calculateSimple (long number1, long number2, String Op) throws RemoteException {
        
		long result = 0;
		
        try { 
           switch(Op){
               case "+":
                    	result = number1 + number2; 
               break;
               case "-":        
            	   		result = number1 - number2;
               break;
               case "*":
            	   		result = number1 * number2;
               break;
               case "/":
            	   		result = number1 / number2;					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public long calculateComplex (long number1, long number2, String Op) throws RemoteException {
		
		long result = 1;
		
		if (Op.equals("Pow")){
			result = power(number1, number2);
		} else if(Op.equals("Rad")){
			result = (long)Math.pow(number1, (1.0/number2));
		}

		return result;
	}
        
	public void setName(String name) throws RemoteException{
		this.name = name;
	}

	public String getName() throws RemoteException{
		return this.name;
	}
}
