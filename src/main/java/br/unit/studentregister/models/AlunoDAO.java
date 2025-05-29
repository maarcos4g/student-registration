package br.unit.studentregister.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
  private static final String FILE_NAME = "alunos.txt";

  public List<AlunoModel> listStudents(String registration) {
    List<AlunoModel> students = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
          String name = parts[0].trim();
          String email = parts[1].trim();
          String course = parts[2].trim();
          String reg = parts[3].trim();
          LocalDate birthDate = LocalDate.parse(parts[4].trim());

          AlunoModel student = new AlunoModel(name, email, course, reg, birthDate);
          students.add(student);

          if (registration != null && registration.equals(reg)) {
            return List.of(student);
          }
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
              student.getBirthDate().toString()));
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
