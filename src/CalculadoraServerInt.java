/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;

/**
 *
 * @author ti
 */
 public interface CalculadoraServerInt extends Remote{

	public void connect (CalculadoraEscravaInt slave) throws RemoteException;

	public long calculate(long number1, long number2, String Op) throws RemoteException;

  
}
   
