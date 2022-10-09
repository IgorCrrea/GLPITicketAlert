package br.com.igorcrrea.glpiticketalert.ui;

import br.com.igorcrrea.glpiticketalert.interfaces.FramePattern;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.util.JsonParser;
import br.com.igorcrrea.glpiticketalert.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.List;

public class PopUp extends Frame implements FramePattern{

    @Serial
    private static final long serialVersionUID = 6160448917528208976L;

    private final java.util.Properties PROP = Properties.getProp();
    private final Integer Y = Integer.parseInt(PROP.getProperty("position.height"));
    private final Integer TIME = Integer.parseInt(PROP.getProperty("update.updateTime")) * 60 * 300;
    private final Integer CENTER = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    private final JPanel buttonPane = new JPanel();
    private final JButton hide = new JButton("Hide");
    private final JButton close = new JButton("Close");
    private final JButton config = new JButton("Config");
    private Boolean running = Boolean.TRUE;
    private Integer height;
    private Integer width;
    private final JsonParser jsonParser = new JsonParser();

    public PopUp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    public void run() {
        createButtons();

        while (running) {
            try {
                if(jsonParser.parseData().isPresent()){update(jsonParser.parseData().get());}
                else {setVisible(false);}
            } catch (Exception e) {
                error("Connection Error");
            } finally {
                System.gc();
                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void update(List<Data> list) {
        width = 0;
        height = 85;
        this.setExtendedState(NORMAL);
        removeAll();

        list.forEach(item -> {
            String title = item.getTitle();
            addLabel(title);
            if (title.length() > width) {
                width = title.length();
            }
            height += 35;
        });

        setBounds(CENTER - ((width * 20) / 2), Y, width * 20, height);
        add(buttonPane);
        setVisible(true);
    }

    private void addLabel(String title) {
        JLabel label = new JLabel(title);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label);
    }

    private void error(String error) {
        width = 20;
        height = 120;
        this.setExtendedState(NORMAL);
        setVisible(false);
        removeAll();
        setBounds(CENTER - ((width * 18) / 2), Y, width * 18, height);
        addLabel(error);
        add(buttonPane);
        setVisible(true);
    }

    private void createButtons() {
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPane.setBackground(getForeground());
        buttonPane.add(close);
        buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPane.add(hide);
        buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPane.add(config);

        hide.addActionListener(e -> this.setExtendedState(ICONIFIED));

        close.addActionListener(e -> close());

        config.addActionListener(e -> {
            Thread thread = new Thread(new Login());
            thread.start();
            this.dispose();
            running = Boolean.FALSE;
        });
    }

}