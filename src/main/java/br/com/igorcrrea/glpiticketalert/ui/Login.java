package br.com.igorcrrea.glpiticketalert.ui;


import br.com.igorcrrea.glpiticketalert.interfaces.FramePattern;
import br.com.igorcrrea.glpiticketalert.model.Configurations;
import br.com.igorcrrea.glpiticketalert.util.ConnectionAPI;
import br.com.igorcrrea.glpiticketalert.util.LoginUtils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends Frame implements FramePattern{

    private final Integer HORIZONTAL_CENTER = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    private final Integer VERTICAL_CENTER = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private final JPanel urlPanel = new JPanel();
    private final JPanel userTokenPanel = new JPanel();
    private final JPanel appTokenPanel = new JPanel();
    private final JPanel buttonPane = new JPanel();
    private final JButton done = new JButton("Done");
    private final JButton close = new JButton("Close");

    public Login() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {close();}
        });
    }

    @Override
    public void run() {

        int height = 300;
        int width = 250;
        setBounds(HORIZONTAL_CENTER - width / 2, VERTICAL_CENTER / 2, width, height);

        this.setExtendedState(NORMAL);

        JTextField inputUrlPanel = new JTextField(LoginUtils.readFile().getUrl());
        createField(inputUrlPanel, urlPanel, "URL:");

        JTextField inputUserToken = new JTextField(LoginUtils.readFile().getUserToken());
        createField(inputUserToken, userTokenPanel, "User Token:");

        JTextField inputAppToken = new JTextField(LoginUtils.readFile().getAppToken());
        createField(inputAppToken, appTokenPanel, "App Token:");

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPane.setBackground(getForeground());
        buttonPane.add(done);
        buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPane.add(close);

        done.addActionListener(e -> {

            //Get input texts
            String inputUrlPanelText = inputUrlPanel.getText();
            String inputAppTokenText = inputAppToken.getText();
            String inputUserTokenText = inputUserToken.getText();

            //Configure credentials
            Configurations configurations = new Configurations(inputUrlPanelText, inputAppTokenText, inputUserTokenText);
            LoginUtils.writeFile(configurations);
            ConnectionAPI.updateSession();

            //Render PopUp Screen
            PopUp popUp = new PopUp();
            Thread popUpThread = new Thread(popUp, "PopUp Thread");
            popUpThread.start();
            this.dispose();
        });

        close.addActionListener(e -> close());

        this.add(createLabel("Configuration", 15));
        this.add(urlPanel);
        this.add(userTokenPanel);
        this.add(appTokenPanel);
        this.add(buttonPane);

        setVisible(true);
    }

    private JLabel createLabel(String title, Integer fontSize) {
        JLabel label = new JLabel(title);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        return label;
    }

    private void createField(JTextField textField, JPanel panel, String title){
        panel.setLayout(new GridLayout(2,1));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 0, 10));
        panel.setBackground(getForeground());
        panel.add(createLabel(title, 13));
        panel.add(textField);
    }

}