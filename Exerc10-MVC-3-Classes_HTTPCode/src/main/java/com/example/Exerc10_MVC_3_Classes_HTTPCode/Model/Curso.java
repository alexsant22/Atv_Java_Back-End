package com.example.Exerc10_MVC_3_Classes_HTTPCode.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    // Atributos
    private int idCurso;
    private String nome;
    private int numeroSala;
    private ArrayList<Aluno> alunos;
    private Professor professor;
}
