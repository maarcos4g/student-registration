package br.unit.studentregister.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class AlunoView extends JFrame {
  private DefaultTableModel tableModel;

  public AlunoView() {
    setTitle("Listagem de Alunos");
    setSize(getPreferredSize());
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
    setVisible(true);
  }

  private void initComponents() {
    setLayout(new BorderLayout());

    JPanel topPanel = new JPanel();

    JButton addButton = new JButton("Adicionar novo aluno");
    addButton.setBackground(new Color(156, 39, 176));
    addButton.setForeground(Color.WHITE);

    topPanel.add(addButton);
    add(topPanel, BorderLayout.NORTH);
  }
}
