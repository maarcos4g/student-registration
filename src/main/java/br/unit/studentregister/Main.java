package br.unit.studentregister;

import br.unit.studentregister.view.AlunoView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
          new AlunoView();
        });
    }
}