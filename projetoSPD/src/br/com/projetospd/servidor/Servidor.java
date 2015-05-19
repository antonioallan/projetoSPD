package br.com.projetospd.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] args) throws IOException	{
		int n1;
		int n2;
		ServerSocket servidor = new ServerSocket(1234);
		System.out.println("Porta 1234 aberta!");
		Socket cliente = servidor.accept();
		System.out.println("Uma nova Conexão aberta "
				+ cliente.getInetAddress().getHostAddress());
		
		Scanner s = new Scanner(cliente.getInputStream());
		PrintStream saidaCliente = new PrintStream(cliente.getOutputStream()); 			
			switch (s.nextLine()) {
			case "SOMA":
				n1 = s.nextInt();
				n2 = s.nextInt();
				saidaCliente.println(n1 + " + " + n2 + " = "+ (n1+n2));
				break;				
			case "SUBTRACAO":
				n1 = s.nextInt();
				n2 = s.nextInt();
				saidaCliente.println(n1 + " - "+ n2 + " = "+ (n1-n2));		
				break;
			case "MULTIPLICACAO":
				n1 = s.nextInt();
				n2 = s.nextInt();
				saidaCliente.println(n1 + " * "+ n2 + " = "+ (n1*n2));
				break;	
			case "DIVICAO":
				n1 = s.nextInt();
				n2 = s.nextInt();
					
				saidaCliente.println(n1 + " / "+ n2 + " = "+ (n1/n2));
				break;
			default:
				saidaCliente.println("Erro na Operação selecionada");
				break;
			}
		s.close();
		servidor.close();
		cliente.close();
		
	}
}
