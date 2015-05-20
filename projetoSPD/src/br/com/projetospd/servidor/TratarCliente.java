package br.com.projetospd.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TratarCliente implements Runnable {
	private Socket cliente;

	public TratarCliente(Socket c) throws IOException {
		cliente = c;
	}

	public void execulta() throws IOException {

		Scanner s = new Scanner(cliente.getInputStream());
		PrintStream saidaCliente = new PrintStream(cliente.getOutputStream());
		double n1;
		double n2;
		String p;
		String re;
		if (s.hasNextLine()) {
			try {
				n1 = Double.parseDouble(s.nextLine());
				p = s.nextLine();
				if (p.equals("limpar")) {
					execulta();
				}
				n2 = Double.parseDouble(s.nextLine());
				re = s.nextLine();
			} catch (Exception e) {
				p = "limpar";
				n1 = 0;
				n2 = 0;
				re = "";
			}
			double resultado = 0;

			switch (p) {
			case "SOMA":
				resultado = (n1 + n2);
				break;
			case "SUBTRACAO":
				resultado = (n1 - n2);
				break;
			case "MULTIPLICACAO":
				resultado = (n1 * n2);
				break;
			case "DIVICAO":
				resultado = (n1 / n2);
				break;
			case "limpar":
				execulta();
				break;
			default:
				saidaCliente.println("Erro na Operação selecionada");
				break;
			}
			if (re.equals("=")) {
				saidaCliente.println(resultado);
				execulta();
			}
		}
		s.close();
	}

	@Override
	public void run() {
		try {
			execulta();
			cliente.close();
		} catch (Exception e) {

		}
	}

}
