package com.example.Exerc10_MVC_3_Classes_HTTPCode.View;

import com.example.Exerc10_MVC_3_Classes_HTTPCode.Controller.CursoController;
import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Aluno;
import com.example.Exerc10_MVC_3_Classes_HTTPCode.Model.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoView {
    CursoController cursoController = new CursoController();

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> getAll(
            @RequestParam(required = false) String nomeProfessor,
            @RequestParam(required = false) Integer sala
    ) {
        if (nomeProfessor != null) {
            return cursoController.getByProfessor(nomeProfessor);
        } else if (sala != null) {
            return cursoController.getBySala(sala);
        } else {
            return cursoController.getAll();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Curso> getById(@PathVariable int id) {
        return cursoController.getById(id);
    }

    @PostMapping("/criar")
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        return cursoController.create(curso);
    }

    @PostMapping("/{idCurso}/alunos")
    public ResponseEntity<Curso> adicionarAluno(@PathVariable int idCurso, @RequestBody Aluno aluno) {
        return cursoController.insertAluno(idCurso, aluno);
    }

    @PutMapping("/atualizar/{idCurso}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable("idCurso") int id, @RequestBody Curso cursoAtt) {
        return cursoController.attCurso(id, cursoAtt);
    }

    @PutMapping("/atualizar/{idCurso}/alunos/{idAluno}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable int idCurso, @PathVariable int idAluno, @RequestBody Aluno alunoAtt) {
        return cursoController.attAlunoCurso(idCurso, idAluno, alunoAtt);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        return cursoController.deletar(id);
    }
}
