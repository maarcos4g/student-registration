package br.unit.studentregister.models;

import java.time.LocalDate;

public class AlunoModel {
  private String name;
  private String email;
  private String course;
  private String registration;
  private LocalDate birthDate;

  public AlunoModel(String name, String email, String course, String registration, LocalDate birthDate) {
    this.name = name;
    this.email = email;
    this.course = course;
    this.registration = registration;
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getCourse() {
    return course;
  }

  public String getRegistration() {
    return registration;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }
}
