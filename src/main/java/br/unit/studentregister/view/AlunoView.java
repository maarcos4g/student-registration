package br.unit.studentregister.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import br.unit.studentregister.controllers.AlunoController;
import br.unit.studentregister.models.AlunoModel;

public class AlunoView extends JFrame {
  private DefaultTableModel tableModel;
  private JTable studentTable;
  private JButton addButton;
  private AlunoController controller;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public AlunoView() {
    setTitle("Listagem de Alunos");
    setSize(1280, 832);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
  }

  public void setController(AlunoController controller) {
    this.controller = controller;
    atualizarTabela();
  }

  private void initComponents() {
    setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Listagem");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(titleLabel, BorderLayout.WEST);

    addButton = new JButton("➕ Adicionar novo aluno");
    addButton.setBackground(new Color(156, 39, 176));
    addButton.setForeground(Color.WHITE);
    addButton.setFocusPainted(false);
    addButton.setPreferredSize(new Dimension(220, 40));
    addButton.addActionListener(e -> openForm());

    topPanel.add(addButton, BorderLayout.EAST);
    add(topPanel, BorderLayout.NORTH);

    String[] columnNames = { "Aluno", "Curso", "Matrícula", "idade", "Ações" };
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 4; // Only the "Ações" column is editable
      }
    };

    studentTable = new JTable(tableModel);
    studentTable.setRowHeight(40);
    JScrollPane scrollPane = new JScrollPane(studentTable);
    add(scrollPane, BorderLayout.CENTER);
  }

  private void openForm() {
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField coursField = new JTextField();
    JTextField registrationField = new JTextField();
    JTextField birthDateField = new JTextField();
    // JTextField ageField = new JTextField();

    Object[] message = {
        "Nome:", nameField,
        "Email:", emailField,
        "Curso:", coursField,
        "Matrícula:", registrationField,
        "Data de Nascimento:", birthDateField,
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Adicionar aluno", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
      LocalDate birthDate = LocalDate.parse(birthDateField.getText(), DATE_FORMATTER);

      AlunoModel student = new AlunoModel(
          nameField.getText(),
          emailField.getText(),
          coursField.getText(),
          registrationField.getText(),
          birthDate);
      controller.addStudent(student);
      atualizarTabela();
    }
  }

  public void atualizarTabela() {
    tableModel.setRowCount(0);
    List<AlunoModel> alunos = controller.loadingStudentList();
    for (AlunoModel aluno : alunos) {
      adicionarAlunoNaTabela(aluno);
    }
  }

  public void adicionarAlunoNaTabela(AlunoModel student) {
    int idade = calcularIdade(student.getBirthDate());

    JPanel actionsPanel = new JPanel();
    actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

    JButton deleteButton = new JButton("🗑");
    deleteButton.setForeground(Color.RED);
    deleteButton.addActionListener(e -> {
      // controller.(student.getRegistration());
      atualizarTabela();
    });

    JButton viewButton = new JButton("👁");
    viewButton.setForeground(Color.MAGENTA);
    viewButton.addActionListener(e -> {
      JOptionPane.showMessageDialog(this, student.toString());
    });

    JButton editButton = new JButton("👤");
    editButton.setForeground(Color.CYAN);
    // ação para editar o aluno

    actionsPanel.add(deleteButton);
    actionsPanel.add(viewButton);
    actionsPanel.add(editButton);

    tableModel.addRow(new Object[] {
        "<html>" + student.getName() + "<br>" + student.getEmail() + "</html>",
        student.getCourse(),
        student.getRegistration(),
        idade + " anos",
        "Ações"
    });
  }

  private int calcularIdade(LocalDate dataNascimento) {
    return Period.between(dataNascimento, LocalDate.now()).getYears();
  }

}
