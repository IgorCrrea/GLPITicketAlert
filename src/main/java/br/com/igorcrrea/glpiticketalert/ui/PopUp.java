package br.com.igorcrrea.glpiticketalert.ui;

import br.com.igorcrrea.glpiticketalert.util.Properties;
import br.com.igorcrrea.glpiticketalert.interfaces.FramePattern;
import br.com.igorcrrea.glpiticketalert.model.DataDTO;
import br.com.igorcrrea.glpiticketalert.service.JsonParser;

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

public class PopUp extends Frame implements FramePattern, Runnable{

    @Serial
    private static final long serialVersionUID = 6160448917528208976L;

    // config.properties file properties
    private final java.util.Properties PROP = Properties.getProp();
    private final Integer Y = Integer.parseInt(PROP.getProperty("position.height"));
    private final Integer TIME = Integer.parseInt(PROP.getProperty("update.updateTime")) * 60 * 1000;

    private final Integer CENTER = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;

    // Buttons
    private final JPanel buttonPane = new JPanel();
    private final JButton hide = new JButton("Hide");
    private final JButton close = new JButton("Close");

    private Integer height;
    private Integer width;

    public PopUp() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createButton();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

    }

    public void run() {
        while (true) {
            try {
                List<DataDTO> list = JsonParser.parseData();

                if (list.size() == 0) {
                    setVisible(false);
                } else {
                    update(list);
                }
            } catch (Exception e) {
                error("Connection Error");
            } finally {

                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void update(List<DataDTO> list) {
        width = 0;
        height = 85;
        this.setExtendedState(NORMAL);

        setVisible(false);
        removeAll();

        list.forEach(item -> {
            String title = item.getTitle();
            addLabel(title);
            if (title.length() > width) {
                width = title.length();
            }
            height += 35;
        });

        //position and size
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

    private void createButton() {
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPane.setBackground(getForeground());
        buttonPane.add(close);
        buttonPane.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPane.add(hide);

        hide.addActionListener(e -> this.setExtendedState(ICONIFIED));
        close.addActionListener(e -> close());
    }

}