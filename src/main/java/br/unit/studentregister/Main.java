package br.unit.studentregister;

import br.unit.studentregister.controllers.AlunoController;
import br.unit.studentregister.models.AlunoDAO;
import br.unit.studentregister.view.AlunoView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
          AlunoView view = new AlunoView();
          AlunoDAO dao = new AlunoDAO();
          AlunoController controller = new AlunoController(dao, view);
          view.setController(controller);
          view.setVisible(true);
        });
    }
}