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
    
public class CalculadoraServer{
	public static void main(String[] args) {
		
		try{
			Registry clientReg, svBasicReg, svComplexReg;
			
			// Seção para criar o registro deste servidor p/ ser reconhecido pelo cliente
			// Se o registro do servidor não puder ser criado (pois já existe um), é utilizado o já existente
			try {
				clientReg = LocateRegistry.createRegistry(1099);			    
			} catch (RemoteException e) { 
			    clientReg = LocateRegistry.getRegistry(1099);
			    clientReg.list();
			}
			
			// Alterando o nome do servidor
			CalculadoraServerInt server = new CalculadoraServerImple();
			clientReg.rebind("Server", server);

			// Procurando pelos servidores escravos
			svBasicReg = LocateRegistry.getRegistry("127.0.0.1", 1100);
			svComplexReg = LocateRegistry.getRegistry("127.0.0.1", 1101);
			
			// Conectando aos servidores escravos
			CalculadoraEscravaInt svBasico = (CalculadoraEscravaInt) svBasicReg.lookup("ServerBasico");
			server.connect(svBasico);
		
		

			CalculadoraEscravaInt svComplexo = (CalculadoraEscravaInt) svComplexReg.lookup("ServerComplexo");
			server.connect(svComplexo);	
		
			

		}catch(NotBoundException | RemoteException e){
	     
		}		
	}
}

