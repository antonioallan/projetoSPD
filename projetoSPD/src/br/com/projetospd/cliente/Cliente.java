package br.com.projetospd.cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Cliente {

	private Socket cliente;
	private JFrame janela;
	private JPanel meuPainel;
	private JLabel resultado;
	private static PrintStream saida;
	private static Scanner saidaServidor;
	private int qtdNumero;
	private boolean possueOperador = false;
	private JTextArea info;
	private String numero1 = "";
	private String numero2 = "";
	private boolean limparResult = false;

	public Cliente(String host, int porta)
			throws java.net.UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
		info = new JTextArea();
		cliente = new Socket(host, porta);
		info.setText("Cliente se conectou");
		saida = new PrintStream(cliente.getOutputStream());
		saidaServidor = new Scanner(cliente.getInputStream());
		inicializar();
		mostraTela();
	}

	public void inicializar() {
		janela = new JFrame("Meu primeiro frame em Java");
		meuPainel = new JPanel();
		meuPainel.setLayout(new GridLayout(4, 1));
		resultado = new JLabel();
		meuPainel.add(resultado);
		JPanel painelNumero = new JPanel();
		painelNumero.setLayout(new GridLayout(4, 4));
		montarButao(painelNumero);
		meuPainel.add(painelNumero);
		JButton btnResultado = new JButton("=");
		btnResultado.addActionListener(new acaoButaoResultado());
		meuPainel.add(btnResultado);
		meuPainel.add(info);
	}

	private void montarButao(JPanel panelBotao) {
		// ---linha 1
		JButton n7 = new JButton("7");
		n7.addActionListener(new acaoButaoNumero(7));
		panelBotao.add(n7);
		JButton n8 = new JButton("8");
		n8.addActionListener(new acaoButaoNumero(8));
		panelBotao.add(n8);
		JButton n9 = new JButton("9");
		n9.addActionListener(new acaoButaoNumero(9));
		panelBotao.add(n9);
		JButton btnMultiplicacao = new JButton("x");
		btnMultiplicacao.addActionListener(new acaoButaoOperacao(
				"MULTIPLICACAO", "x"));
		panelBotao.add(btnMultiplicacao);
		// ---linha 2
		JButton n4 = new JButton("4");
		n4.addActionListener(new acaoButaoNumero(4));
		panelBotao.add(n4);
		JButton n5 = new JButton("5");
		n5.addActionListener(new acaoButaoNumero(5));
		panelBotao.add(n5);
		JButton n6 = new JButton("6");
		n6.addActionListener(new acaoButaoNumero(6));
		panelBotao.add(n6);
		JButton btnDivisao = new JButton("/");
		btnDivisao.addActionListener(new acaoButaoOperacao("DIVICAO", "/"));
		panelBotao.add(btnDivisao);
		// --linha3
		JButton n1 = new JButton("1");
		n1.addActionListener(new acaoButaoNumero(1));
		panelBotao.add(n1);
		JButton n2 = new JButton("2");
		n2.addActionListener(new acaoButaoNumero(2));
		panelBotao.add(n2);
		JButton n3 = new JButton("3");
		n3.addActionListener(new acaoButaoNumero(3));
		panelBotao.add(n3);
		JButton btnSubtracao = new JButton("-");
		btnSubtracao.addActionListener(new acaoButaoOperacao("SUBTRACAO", "-"));
		panelBotao.add(btnSubtracao);
		// ---linha 4
		JButton btnlimpar = new JButton("C");
		btnlimpar.addActionListener(new acaoButaoApagar());
		panelBotao.add(btnlimpar);
		JButton n0 = new JButton("0");
		n0.addActionListener(new acaoButaoNumero(0));
		panelBotao.add(n0);
		JButton btnPonto = new JButton(".");
		btnPonto.addActionListener(new acaoButaoPonto());
		panelBotao.add(btnPonto);

		JButton btnSoma = new JButton("+");
		btnSoma.addActionListener(new acaoButaoOperacao("SOMA", "+"));
		panelBotao.add(btnSoma);
	}

	public void mostraTela() {

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(meuPainel);
		janela.setSize(200, 300);
		janela.setVisible(true);
	}

	public class acaoButaoNumero implements ActionListener {

		private int numero;

		public acaoButaoNumero(int numero) {
			this.numero = numero;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (qtdNumero < 2) {
				String result = "";
				if (qtdNumero == 0) {
					if(limparResult){
						resultado.setText("");
						limparResult = false;
					}
					result = numero1 + numero;
					numero1 = result;
				} else if (qtdNumero == 1) {
					result = numero2 + numero;
					numero2 = result;
				}
				resultado.setText(resultado.getText() + numero);
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
			String ultimoChar = resultado.getText().substring(
					resultado.getText().length() - 1);
			if (!resultado.getText().equals("") && !possueOperador
					&& qtdNumero == 0 && !ultimoChar.equals(".")) {
				qtdNumero++;
				possueOperador = true;
				String result = resultado.getText() + " " + simbolo + " ";
				saida.println(numero1);
				saida.println(operacao);
				resultado.setText(result);
			}
		}

	}

	public class acaoButaoPonto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (qtdNumero == 0) {
				numero1 = numero1 + ".";
			} else if (qtdNumero == 1) {
				numero2 = numero2 + ".";
			}
			String result = resultado.getText() + ".";
			resultado.setText(result);

		}

	}

	public class acaoButaoResultado implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String ultimoChar = resultado.getText().substring(
					resultado.getText().length() - 1);
			if (!ultimoChar.equals(".") && !numero2.equals("")) {
				qtdNumero = 0;
				possueOperador = false;
				limparResult = true;
				saida.println(numero2);
				saida.println("=");
				resultado.setText(saidaServidor.nextLine());
			}

		}

	}

	public class acaoButaoApagar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			qtdNumero = 0;
			possueOperador = false;
			saida.println("limpa");
			resultado.setText("");

		}

	}

}
