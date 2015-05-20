package br.com.projetospd.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.crypto.AEADBadTagException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor {
	
	private ServerSocket servidor;
	private Socket cliente;
	private JFrame janela;
	private JPanel meuPainel;
	private JTextArea area;

	public Servidor(int porta) throws IOException {
		inicializar();
		servidor = new ServerSocket(porta);
		area.append("Porta 1234 aberta! \r\n");
		meuPainel.add(area);
		mostraTela();
		execulta();
	}
	
	public void execulta() throws IOException{
		
		while (true) {
			cliente = servidor.accept();
			area.append("Uma nova Conexão aberta " + cliente.getInetAddress().getHostAddress() + "\r\n");
			
			TratarCliente tc = new TratarCliente(cliente);
			new Thread(tc).start();
		}
	}
	
	public void inicializar(){
		
		janela = new JFrame("Servidor Calculadora");
		meuPainel = new JPanel();
		meuPainel.setBounds(5, 5, 580, 380);
		meuPainel.setLayout(null);
		area = new JTextArea();
		area.setBounds(2, 2, 550, 380);
	}
	
	public void mostraTela(){JTextArea area;
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,400);
		janela.setVisible(true);
	}
}
