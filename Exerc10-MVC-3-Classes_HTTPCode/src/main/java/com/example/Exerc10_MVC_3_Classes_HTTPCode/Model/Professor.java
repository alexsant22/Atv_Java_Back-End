package com.example.Exerc10_MVC_3_Classes_HTTPCode.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    // Atributos
    private int idProfessor;
    private String nome;
    private String cpf;
    private double salario;
}
