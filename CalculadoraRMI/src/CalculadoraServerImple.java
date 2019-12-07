/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author ti
 */
public class CalculadoraServerImple extends UnicastRemoteObject implements CalculadoraServerInt 
{
	private ArrayList<CalculadoraEscravaInt> listOfSlaves = new ArrayList();

	public CalculadoraServerImple() throws RemoteException{
		super();
	}

	public void connect (CalculadoraEscravaInt slave) throws RemoteException
	{
		this.listOfSlaves.add(slave);
	}

	public long calculate(long number1, long number2, String Op) throws RemoteException {
		CalculadoraEscravaInt svBasico = new CalculadoraEscravaImpl();
		CalculadoraEscravaInt svComplexo = new CalculadoraEscravaImpl();
            long result = 0;
		try {
			for (int i = 0; i < this.listOfSlaves.size(); i++) {
				CalculadoraEscravaInt slv = (CalculadoraEscravaInt) listOfSlaves.get(i);
				if ("ServerBasico".equals(slv.getName())){
					svBasico = slv;
				} else {
					svComplexo = slv;
				}
			}

			if ("+".equals(Op) || "-".equals(Op) || "*".equals(Op) || "/".equals(Op)) {
				result = svBasico.calculateSimple(number1, number2, Op);
			} else if(Op.equals("Pow") || Op.equals("Rad")){
				result = svComplexo.calculateComplex(number1, number2, Op);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
}
