package br.com.igorcrrea.glpiticketalert.ui;

import java.awt.Font;
import java.awt.Frame;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.google.gson.JsonSyntaxException;

import br.com.igorcrrea.glpiticketalert.Propriedades;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.service.JsonParser;

public class PopUp extends Frame {

	private static final long serialVersionUID = 6160448917528208976L;
	private static final Properties PROP = Propriedades.getProp();
	private static final Integer X = Integer.parseInt(PROP.getProperty("position.posX"));
	private static final Integer Y = Integer.parseInt(PROP.getProperty("position.posY"));
	private static final Integer W = Integer.parseInt(PROP.getProperty("position.width"));
	private static final Integer H = Integer.parseInt(PROP.getProperty("position.height"));
	private static final Integer TIME = Integer.parseInt(PROP.getProperty("update.tempoUpdate"))*60*1000;


	private final JButton jButton = new JButton("Ocultar");
	private List<Data> lista;
	
	public PopUp() {
		//Tira as bordas
		setUndecorated(true);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Posicao e tamanho
		setBounds(X,Y,W,H);
		
		//Adiciona acao ao botao
		jButton.addActionListener(e -> setVisible(false));	
		jButton.setAlignmentX(CENTER_ALIGNMENT);

	}

	public void run() {
		while(true) {
			
			try {
				lista = JsonParser.run();
				
				//Verifica se a lista esta zerada, caso estaja, deixa invisivel.
				if (lista.size() == 0) {
					setVisible(false);
				}
				//Caso tenha itens dentro da lista, atualiza a pagina, e deixa visivel.
				else {
					update(lista);
				}
			} catch (JsonSyntaxException | IOException | InterruptedException e){
				erro("Erro de Conexão");
			} finally {
				
				try {
					Thread.sleep(TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void update(List<Data> lista) {
		setVisible(false);
		removeAll();
		lista.forEach(item -> addLabel(item.getTitulo()));
		add(jButton);
		setVisible(true);
	}
	
	private void addLabel(String titulo) {
		JLabel label = new JLabel(titulo);
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(new Font("Arial", Font.BOLD, 30));
		add(label);
	}
	
	private void erro(String erro) {
		setVisible(false);
		removeAll();
		addLabel(erro);
		add(jButton);
		setVisible(true);
	}

}