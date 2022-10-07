package br.com.igorcrrea.glpiticketalert.interfaces;

import br.com.igorcrrea.glpiticketalert.service.ConnectionAPI;

import javax.swing.JOptionPane;

public interface FramePattern {

    default void close() {
        int selectedOption = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Close", JOptionPane.YES_NO_OPTION);

        // yes = 0, no = 1
        if (selectedOption == 0) {
            ConnectionAPI.Kill();
            System.exit(0);
        }
    }

    void run();

}
