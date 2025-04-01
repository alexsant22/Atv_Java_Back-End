package com.example.Exerc10_MVC_3_Classes_HTTPCode.Controller;

import com.example.Exerc10_MVC_3_Classes_HTTPCode.Banco.CursoBD;
import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Aluno;
import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Curso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CursoController {

    CursoBD repository = new CursoBD();

    public ResponseEntity<List<Curso>> getAll() { // Buscar todos os Cursos
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<List<Curso>> getByProfessor(String nomeProfessor) { // Buscar pelo nome do Professor
        List<Curso> cursos = repository.findByProfessor(nomeProfessor);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    public ResponseEntity<List<Curso>> getBySala(int sala) { // Buscar pelo nmr da Sala
        List<Curso> cursos = repository.findBySala(sala);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    public ResponseEntity<Curso> getById(int id) { // Buscar por ID do Curso
        Curso curso = repository.getById(id);

        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Curso> create(Curso curso) { // Criar Curso
        Curso cursoSave = repository.create(curso);
        return  ResponseEntity.status(HttpStatus.CREATED).body(cursoSave);
    }

    public ResponseEntity<Curso> insertAluno(int idCurso, Aluno aluno) { // Add Aluno novo no curso
        Curso curso = repository.insertAluno(idCurso, aluno);

        if (curso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(curso);
    }

    public ResponseEntity<Curso> attCurso(int id, Curso cursoAtt) { // Atualizar o Curso
        Curso cursoExistente = repository.getById(id);

        if (cursoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean resultado = repository.attCurso(id, cursoAtt);

        if (resultado) {
            Curso cursoAtualizado = repository.getById(id);
            return ResponseEntity.ok(cursoAtualizado);
        } else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Aluno> attAlunoCurso(int idCurso, int idAluno, Aluno alunoAtt) { // Atualizar o Aluno no Curso
        boolean resultado = repository.attAlunoCurso(idCurso, idAluno, alunoAtt);

        if (!resultado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(alunoAtt);
    }

    public ResponseEntity<String> deletar(int id) { // Deletar o curso
        Curso curso = repository.getById(id);

        if (curso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Curso com o ID: " + id + " não encontrado.");
        }

        repository.deletar(curso);
        return ResponseEntity.ok("Curso " + curso.getNome() + " deletado.");
    }
}
