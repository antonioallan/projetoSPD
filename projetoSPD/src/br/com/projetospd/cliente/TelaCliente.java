package br.com.projetospd.cliente;

public class TelaCliente {
	
	public static void main(String[] args) {
		try {
			Cliente c = new Cliente("127.0.0.1", 1234);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
