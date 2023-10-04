package com.squad3.bemestar.controller;


import com.squad3.bemestar.domain.dto.PerguntaDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.service.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/perguntas")

public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;
//    @GetMapping
//    public ResponseEntity<List<Perguntas>> listarPerguntas() {
//        List<Perguntas> perguntas = perguntasService.listarPerguntas();
//        return ResponseEntity.ok(perguntas);
//    }

    @PostMapping
    public ResponseEntity<Perguntas> adicionaPergunta(@RequestBody Perguntas pergunta) {
       Perguntas novaPergunta = perguntasService.adicionaPergunta(pergunta);
               return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);

    }
}
