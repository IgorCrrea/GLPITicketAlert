package br.com.igorcrrea.glpiticketalert;

import java.io.IOException;
import java.util.List;

import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.service.JsonParser;
import br.com.igorcrrea.glpiticketalert.ui.PopUp;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		PopUp popUp = new PopUp();

		while (true) {
			
			List<Data> lista = JsonParser.run();
			
			if (lista.size() > 0) {
				popUp.update(lista);
				popUp.setVisible(true);
			} else {
				popUp.setVisible(false);
			}
			Thread.sleep(180000);
			popUp.removeAll();
		}

	}

}
