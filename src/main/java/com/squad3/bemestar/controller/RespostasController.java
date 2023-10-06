package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.RespostasDTO;
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

    //Endpoint para adicionar uma nova resposta
    @PostMapping
    public ResponseEntity<Respostas> adicionaResposta(@RequestBody Respostas respostas) {
        Respostas novaResposta = respostasService.adicionaResposta(respostas);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);
    }

    //Endpoint para listar todas as respostas
    @GetMapping
    public ResponseEntity<List<Respostas>> listarRespostas() {
        List<Respostas> respostas = respostasService.listarRespostas();
        return ResponseEntity.ok(respostas);
    }
//
//    //Endpoint para adicionar uma nova resposta conforme DTO
//    @PostMapping("/dto")
//    public ResponseEntity<RespostasDTO> adicionaRespostaDTO(@RequestBody RespostasDTO respostasDTO) {
//        RespostasDTO novaResposta = respostasService.adicionaRespostaDTO(respostasDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);
//    }
    //Endpoint para listar todas as respostas conforme DTO
    @GetMapping("/dto")
    public ResponseEntity<List<RespostasDTO>> listarRespostasDTO() {
        List<RespostasDTO> respostas = respostasService.listarRespostasDTO();
        return ResponseEntity.ok(respostas);
    }

    //Endpoint para listar todas as respostas por id
    @GetMapping("/{id}")
    public ResponseEntity<RespostasDTO> listarRespostaPorId(@PathVariable Long id) {
        RespostasDTO respostas = respostasService.listarRespostaPorId(id);
        if (respostas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(respostas);
        }
    }


}
