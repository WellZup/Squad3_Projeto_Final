package com.squad3.bemestar.controller;


import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.service.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/perguntas")

public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Perguntas>> listarPerguntas() {
        List<Perguntas> perguntas = perguntasService.listarPerguntas();
        return ResponseEntity.ok(perguntas);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Perguntas> buscarPorId(@PathVariable("id") Long id) {
        try {
            Perguntas perguntas = perguntasService.buscarPorId(id);
            return ResponseEntity.ok(perguntas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Perguntas> adicionaPergunta(@RequestBody Perguntas pergunta) {
        Perguntas novaPergunta = perguntasService.adicionaPergunta(pergunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPergunta(@PathVariable Long id) {
        try {
            perguntasService.deletarPergunta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint para listar todas as perguntas de uma campanha especifica
    @GetMapping("/{campanhaId}/perguntas")
    public ResponseEntity<List<Perguntas>> listarPerguntasPorCampanha(@PathVariable Long campanhaId) {
        List<Perguntas> perguntas = perguntasService.listarPerguntasPorCampanha(campanhaId);
        return ResponseEntity.ok(perguntas);
    }
}
