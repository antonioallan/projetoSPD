package br.com.projetospd.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket cliente = new Socket("127.0.0.1",1234);
		System.out.println("Cliente se conectou");
		
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		
		while(teclado.hasNextLine()){
			saida.println(teclado.nextLine());
		}
		
		saida.close();
		teclado.close();
		cliente.close();
	}

}
