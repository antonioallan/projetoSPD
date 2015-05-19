package br.com.projetospd.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket cliente = new Socket("127.0.0.1", 1234);
        System.out.println("Cliente se conectou");

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        Scanner saidaServidor = new Scanner(cliente.getInputStream());

        System.out.println("Informe a opereçao realizada(SOMA,SUBTRACAO,MULTIPLICACAO,DIVISAO)?");
        saida.println(teclado.nextLine());
        System.out.println("Informe o 1º numero");
        saida.println(teclado.nextLine());
        System.out.println("Informe o 2º numero");
        saida.println(teclado.nextLine());
        System.out.println(saidaServidor.nextLine());
        saida.close();
        teclado.close();
        cliente.close();
        saidaServidor.close();
	}

}
