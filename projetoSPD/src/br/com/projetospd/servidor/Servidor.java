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
		cliente = servidor.accept();
		tratandoCliente();
	}
	
	public void run(){
		while (true) {

			  //cliente = servidor.accept();

			  // cria um objeto que vai tratar a conexão
			  //TratamentoClass tratamento = new TratamentoClass(cliente);

			  // cria a thread em cima deste objeto
			  //Thread t = new Thread(tratamento);

			  // inicia a thread
			  //t.start();

			}
	}
	
	public void tratandoCliente() throws IOException{
		area.append("Uma nova Conexão aberta " + cliente.getInetAddress().getHostAddress() + "\r\n");
		Scanner s = new Scanner(this.cliente.getInputStream());
		PrintStream saidaCliente = new PrintStream(cliente.getOutputStream()); 			
			double n1;
			double n2;
			n1 = Double.parseDouble(s.nextLine());
			String p = s.nextLine();
			n2 = Double.parseDouble(s.nextLine());
			String re = s.nextLine(); 
			double resultado = 0;
			
			switch (p) {
			case "SOMA":
				resultado = (n1+n2);
				
				break;				
			case "SUBTRACAO":
				resultado = (n1-n2);		
				break;
			case "MULTIPLICACAO":
				resultado = (n1*n2);
				break;	
			case "DIVICAO":
				resultado = (n1/n2);
				break;
				
			case "limpar":
				saidaCliente.println("fazer ação");
				break;
			default:
				saidaCliente.println("Erro na Operação selecionada");
				break;
			}
			if(re.equals("=")){
				saidaCliente.println(resultado);
			}
			
		s.close();
		servidor.close();
		cliente.close();
	}
	
	public void inicializar(){
		
		janela = new JFrame("Meu primeiro frame em Java");
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
