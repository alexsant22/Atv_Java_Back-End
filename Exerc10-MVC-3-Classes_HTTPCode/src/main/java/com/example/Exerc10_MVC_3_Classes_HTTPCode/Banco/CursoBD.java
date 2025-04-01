package com.example.Exerc10_MVC_3_Classes_HTTPCode.Banco;

import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Aluno;
import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Curso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CursoBD {
    public List<Curso> cursos;

    public CursoBD() {
        this.cursos = new ArrayList<>();
    }

    public List<Curso> findAll() { // Buscar todos
        return cursos.stream()
                .sorted(Comparator.comparing(Curso::getNome).reversed())
                .toList();
    }

    public List<Curso> findByProfessor(String nomeProfessor) { // Busca pelo nome do Professor
        return cursos.stream()
                .filter(curso -> curso.getProfessor().getNome().equals(nomeProfessor))
                .toList();
    }

    public List<Curso> findBySala(int sala) { // Buscar por nmr da Sala
        return cursos.stream()
                .filter(curso -> curso.getNumeroSala() == sala)
                .toList();
    }

    public Curso getById(int id) { // Buscar por id
        return cursos.stream()
                .filter(curso -> curso.getIdCurso() == id)
                .findFirst()
                .orElse(null);
    }

    public Curso create(Curso curso) { // Criando Curso
        cursos.add(curso);
        return curso;
    }

    public Curso insertAluno(int idCurso, Aluno aluno) { // Inserindo Aluno novo no curso
        Curso cursoBd = cursos.stream()
                .filter(c -> c.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);

        if (cursoBd == null) {
            return null;
        }

        cursoBd.getAlunos().add(aluno);

        return cursoBd;
    }

    public boolean attCurso(int id, Curso cursoAtt) { // Atualizar curso
        Curso curso = cursos.stream()
                .filter(c -> c.getIdCurso() == id)
                .findFirst()
                .orElse(null);

        if (curso == null) {
            return false;
        }

        curso.setNome(cursoAtt.getNome());
        curso.setNumeroSala(cursoAtt.getNumeroSala());
        curso.setProfessor(cursoAtt.getProfessor());

        return true;
    }

    public boolean attAlunoCurso(int idCurso, int idAluno, Aluno alunoAtt) { // Atualiza um Aluno dentro do Curso
        Curso curso = cursos.stream()
                .filter(c -> c.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);

        if (curso == null) {
            return false;
        }

        Aluno aluno = curso.getAlunos().stream()
                .filter(a -> a.getIdAluno() == idAluno)
                .findFirst()
                .orElse(null);

        if (aluno == null) {
            return false;
        }

        aluno.setNome(alunoAtt.getNome());
        aluno.setCpf(alunoAtt.getCpf());

        return true;
    }

    public boolean deletar(Curso curso) { // Deletar Curso
        cursos.remove(curso);

        return true;
    }
}
