/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ti
 */
    public interface CalculadoraEscravaInt extends Remote{

	public long calculateSimple (long number1, long number2, String Op) throws RemoteException;

	public long calculateComplex (long number1, long number2, String Op) throws RemoteException;

	public void setName(String name) throws RemoteException;

	public String getName() throws RemoteException;
}
    

