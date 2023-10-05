package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.service.RespostasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/respostas")

public class RespostasController {

    @Autowired
    private RespostasService respostasService;


    @GetMapping
    public ResponseEntity<List<Respostas>> listarRespostas() {
        List<Respostas> respostas = respostasService.listarRespostas();
        return ResponseEntity.ok(respostas);
    }

    @PostMapping
    public ResponseEntity<Respostas> adicionaResposta(@RequestBody Respostas resposta) {
        Respostas novaResposta = respostasService.adicionaResposta(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Respostas> listarRespostaPorId(@PathVariable Long id) {
        Respostas respostas = respostasService.listarRespostaPorId(id);
        if (respostas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(respostas);
        }

    }


}
