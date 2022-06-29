package br.com.igorcrrea.glpiticketalert.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import br.com.igorcrrea.glpiticketalert.Propriedades;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.service.ConnectionAPI;
import br.com.igorcrrea.glpiticketalert.service.JsonParser;

public class PopUp extends Frame {

	private static final long serialVersionUID = 6160448917528208976L;
	
	private final Properties PROP = Propriedades.getProp();
	private final Integer Y = Integer.parseInt(PROP.getProperty("position.altura"));
	private final Integer TIME = Integer.parseInt(PROP.getProperty("update.tempoUpdate")) * 60 * 1000;
	
	private final Integer CENTER = (int)Toolkit.getDefaultToolkit()
												.getScreenSize()
												.getWidth()/2;
	
	private Integer heigth = 40;
	private Integer width;

	private final JButton jButton = new JButton("Ocultar");
	private List<Data> lista;

	public PopUp() {
		// Tira as bordas
		setUndecorated(true);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		

		// Adiciona acao ao botao
		jButton.addActionListener(e -> setVisible(false));
		jButton.setAlignmentX(CENTER_ALIGNMENT);

		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				// caixa de dialogo retorna um inteiro
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja Fechar?", "Fechar",
						JOptionPane.YES_NO_OPTION);

				// sim = 0, nao = 1
				if (resposta == 0) {
					ConnectionAPI.Kill();
					System.exit(0);
				}

			}
		});

		
		run();
	}

	public void run() {
		while (true) {

			try {
				lista = JsonParser.run();

				// Verifica se a lista esta zerada, caso estaja, deixa invisivel.
				if (lista.size() == 0) {
					setVisible(false);
				}
				// Caso tenha itens dentro da lista, atualiza a pagina, e deixa visivel.
				else {
					update(lista);
				}
			} catch (JsonSyntaxException | IOException | InterruptedException e) {
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
		width = 0;
		heigth = 35;
		
		setVisible(false);
		removeAll();
		
		lista.forEach(item -> {
			addLabel(item.getTitulo());
			if(item.getTitulo().length() > width) {
				width = item.getTitulo().length();
			}
			heigth += 35;
		});

		// Posicao e tamanho
		setBounds(CENTER - ((width*18)/2), Y, width*18, heigth);
		
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
		setBounds(CENTER - ((width*18)/2), Y, 300, 80);
		addLabel(erro);
		add(jButton);
		setVisible(true);
	}

}