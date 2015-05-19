package br.com.projetospd.cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Cliente {

	private static Socket cliente;
	private JFrame janela;
	private JPanel meuPainel;
	private JTextArea area;
	private JLabel resultado;
	private static PrintStream saida;
	private static Scanner saidaServidor;
	private int qtdNumero;
	private boolean possueOperador = false;

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		cliente = new Socket("127.0.0.1", 1234);
		System.out.println("Cliente se conectou");

		Scanner teclado = new Scanner(System.in);
		saida = new PrintStream(cliente.getOutputStream());
		saidaServidor = new Scanner(cliente.getInputStream());

		System.out
				.println("Informe a opereçao realizada(SOMA,SUBTRACAO,MULTIPLICACAO,DIVISAO)?");
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

	public void inicializar() {

		janela = new JFrame("Meu primeiro frame em Java");
		meuPainel = new JPanel();
		meuPainel.setLayout(new GridLayout(3, 1));
		resultado = new JLabel();
		meuPainel.add(resultado);
		JPanel painelNumero = new JPanel();
		painelNumero.setLayout(new GridLayout(4, 4));
		JButton n9 = new JButton("9");
		painelNumero.add(n9);
		JButton n8 = new JButton("8");
		painelNumero.add(n8);
		JButton n7 = new JButton("7");
		painelNumero.add(n8);

		meuPainel.add(painelNumero);
	}

	public void mostraTela() {

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(meuPainel);
		janela.setSize(600, 400);
		janela.setVisible(true);
	}

	public class acaoButaoNumero implements ActionListener {

		private int numero;

		public acaoButaoNumero(int numero) {
			this.numero = numero;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (qtdNumero < 2) {
				qtdNumero++;
				String result = resultado.getText() + " " + numero;
				saida.println(numero);
				resultado.setText(result);
			}
		}

	}

	public class acaoButaoOperacao implements ActionListener {

		String operacao;
		String simbolo;

		public acaoButaoOperacao(String operacao, String simbolo) {
			this.operacao = operacao;
			this.simbolo = simbolo;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(qtdNumero == 1 && !possueOperador){
			possueOperador = true;
			String result = resultado.getText() + " " + simbolo;
			saida.println(operacao);
			resultado.setText(result);
			}
		}

	}
	
	public class acaoButaoResultado implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			qtdNumero = 0;
			possueOperador = false;
			resultado.setText(saidaServidor.nextLine());

		}

	}

}
