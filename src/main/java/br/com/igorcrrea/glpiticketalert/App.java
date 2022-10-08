package br.com.igorcrrea.glpiticketalert;

import br.com.igorcrrea.glpiticketalert.ui.Login;

public class App {

	public static void main(String[] args) {
		Thread thread = new Thread(new Login(), "Login Thread");
		thread.start();
	}
}
