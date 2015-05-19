package br.com.projetospd.servidor;

import java.io.IOException;

public class TelaServidor {
	public static void main(String[] args) {
		try {
			Servidor s = new Servidor(1234);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
