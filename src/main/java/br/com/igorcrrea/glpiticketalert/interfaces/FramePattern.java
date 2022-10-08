package br.com.igorcrrea.glpiticketalert.interfaces;

import br.com.igorcrrea.glpiticketalert.util.ConnectionAPI;

import javax.swing.JOptionPane;

public interface FramePattern extends Runnable {

    default void close() {
        Integer selectedOption = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Close", JOptionPane.YES_NO_OPTION);

        if (selectedOption == 0) {
            ConnectionAPI.Kill();
            System.exit(0);
        }
    }

}
