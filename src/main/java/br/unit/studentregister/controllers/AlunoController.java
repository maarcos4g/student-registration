package br.unit.studentregister.controllers;

import java.util.List;

import br.unit.studentregister.models.AlunoDAO;
import br.unit.studentregister.models.AlunoModel;
import br.unit.studentregister.view.AlunoView;

public class AlunoController {
  private AlunoDAO alunoDAO;
  private AlunoView alunoView;

  public AlunoController(AlunoDAO alunoDAO, AlunoView alunoView) {
    this.alunoDAO = alunoDAO;
    this.alunoView = alunoView;

    loadingStudentList();
  }

  public List<AlunoModel> loadingStudentList() {
    return alunoDAO.listStudents();
  }

  public void addStudent(AlunoModel student) {
    alunoDAO.saveAluno(student);
    alunoView.atualizarTabela();
  }
}
