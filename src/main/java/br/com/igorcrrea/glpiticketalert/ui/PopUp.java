package br.com.igorcrrea.glpiticketalert.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonSyntaxException;

import br.com.igorcrrea.glpiticketalert.Propriedades;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.service.ConnectionAPI;
import br.com.igorcrrea.glpiticketalert.service.JsonParser;

public class PopUp extends Frame {

	private static final long serialVersionUID = 6160448917528208976L;

	// Propriedades do arquivo config.properties
	private final Properties PROP = Propriedades.getProp();
	private final Integer Y = Integer.parseInt(PROP.getProperty("position.altura"));
	private final Integer TIME = Integer.parseInt(PROP.getProperty("update.tempoUpdate")) * 60 * 1000;

	// Centro da tela
	private final Integer CENTER = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;

	// Botoes
	private final JPanel buttonPane = new JPanel();
	private final JButton ocultar = new JButton("Ocultar");
	private final JButton fechar = new JButton("Fechar");

	// Altura e largura
	private Integer heigth;
	private Integer width;

	private List<Data> lista;

	public PopUp() {
		// Tira as bordas
		setUndecorated(true);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		createButton();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fechar();
			}
		});

		run();
	}

	public void run() {
		while (true) {
			try {
				lista = JsonParser.run();

				if (lista.size() == 0) {
					setVisible(false);
				} else {
					update(lista);
				}
			} catch (JsonSyntaxException | IOException | InterruptedException e) {
				erro("Erro de Conexão");
			} finally {

				try {
					Thread.sleep(TIME);
					// Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void update(List<Data> lista) {
		width = 0;
		heigth = 60;
		this.setExtendedState(NORMAL);

		setVisible(false);
		removeAll();

		lista.forEach(item -> {
			String titulo = item.getTitulo();
			addLabel(titulo);
			if (titulo.length() > width) {
				width = titulo.length();
			}
			heigth += 35;
		});

		// Posicao e tamanho
		setBounds(CENTER - ((width * 18) / 2), Y, width * 18, heigth);

		add(buttonPane);
		setVisible(true);
	}

	private void addLabel(String titulo) {
		JLabel label = new JLabel(titulo);
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(new Font("Arial", Font.BOLD, 30));
		add(label);
	}

	private void erro(String erro) {
		width = 14;
		heigth = 80;
		this.setExtendedState(NORMAL);
		setVisible(false);
		removeAll();
		setBounds(CENTER - ((width * 18) / 2), Y, width * 18, heigth);
		addLabel(erro);
		add(buttonPane);
		setVisible(true);
	}

	private void fechar() {
		Integer resposta = JOptionPane.showConfirmDialog(null, "Deseja Fechar?", "Fechar", JOptionPane.YES_NO_OPTION);

		// sim = 0, nao = 1
		if (resposta == 0) {
			ConnectionAPI.Kill();
			System.exit(0);
		}
	}

	private void createButton() {
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonPane.setBackground(getForeground());
		buttonPane.add(fechar);
		buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
		buttonPane.add(ocultar);

		ocultar.addActionListener(e -> this.setExtendedState(ICONIFIED));
		fechar.addActionListener(e -> fechar());
	}

}