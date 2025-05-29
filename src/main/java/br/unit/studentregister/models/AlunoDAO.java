package br.unit.studentregister.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
  private static final String FILE_NAME = "alunos.txt";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  static {
    try {
      File arquivo = new File(FILE_NAME);
      if (!arquivo.exists()) {
        arquivo.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<AlunoModel> listStudents() {
    List<AlunoModel> students = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(";");
        if (parts.length == 5) {
          String name = parts[0].trim();
          String email = parts[1].trim();
          String course = parts[2].trim();
          String reg = parts[3].trim();
          LocalDate birthDate = LocalDate.parse(parts[4].trim(), DATE_FORMATTER);

          AlunoModel student = new AlunoModel(name, email, course, reg, birthDate);
          students.add(student);
        }

      }
    } catch (IOException error) {
      error.printStackTrace();
    }

    return students;
  }

  public void saveAluno(AlunoModel student) {
    try (BufferedWriter bufferedReader = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
      bufferedReader.write(
          String.join(";",
              student.getName(),
              student.getEmail(),
              student.getCourse(),
              student.getRegistration(),
              student.getBirthDate().format(DATE_FORMATTER)));
      bufferedReader.newLine();
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public void deleteAluno(String registration) {
    // Code to delete aluno by registration from FILE_NAME
  }

  public AlunoModel listAluno() {
    // Code to list all alunos from FILE_NAME
    return null; // Placeholder return
  }
}
