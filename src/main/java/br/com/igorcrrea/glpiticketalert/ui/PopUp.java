package br.com.igorcrrea.glpiticketalert.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.igorcrrea.glpiticketalert.model.Data;

public class PopUp extends Frame {

	private static final long serialVersionUID = 6160448917528208976L;

	JButton jButton = new JButton("Close");
	public PopUp() {

		setUndecorated(true);
		setTitle("Novos Chamado");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(300, 300, 800, 350);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}
	
	public void update(List<Data> lista) {
		
		lista.forEach(item -> {
			JLabel label = new JLabel(item.getTitulo());
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setFont(new Font("Arial", Font.BOLD, 30));
			add(label);
		});

		this.jButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jButton);
	}
}