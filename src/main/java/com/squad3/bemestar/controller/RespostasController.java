package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.RespostasDTO;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.exception.RespostaCreationException;
import com.squad3.bemestar.exception.RespostaNotFoundException;
import com.squad3.bemestar.service.RespostasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite adicionar uma nova Resposta", description = "Adicionar Resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta adicionada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao adicionar resposta!")
    })

    //Endpoint para adicionar uma nova resposta
    @PostMapping
    public ResponseEntity<Respostas> adicionaResposta(@RequestBody Respostas respostas) {
        try {

        Respostas novaResposta = respostasService.adicionaResposta(respostas);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);
        }catch (Exception e) {
            throw new RespostaCreationException("Erro ao criar a resposta: " + e.getMessage());
        }
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar todas as Respostas", description = "Listar Respostas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma resposta encontrada!")
    })

    //Endpoint para listar todas as respostas
    @GetMapping
    public ResponseEntity<List<Respostas>> listarRespostas() {
        List<Respostas> respostas = respostasService.listarRespostas();
        return ResponseEntity.ok(respostas);
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar todas as Respostas conforme DTO", description = "Listar Respostas DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma resposta encontrada!")
    })

    //Endpoint para listar todas as respostas conforme DTO
    @GetMapping("/dto")
    public ResponseEntity<List<RespostasDTO>> listarRespostasDTO() {
        List<RespostasDTO> respostas = respostasService.listarRespostasDTO();
        return ResponseEntity.ok(respostas);
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar Respostas por ID", description = "Listar Respostas por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Resposta não encontrada!")
    })

    //Endpoint para listar todas as respostas por id
    @GetMapping("/{id}")
    public ResponseEntity<RespostasDTO> listarRespostaPorId(@PathVariable Long id) {
        RespostasDTO respostas = respostasService.listarRespostaPorId(id);
        if (respostas == null) {
            throw new RespostaNotFoundException("Resposta não encontrada para o ID: " + id);
        } else {
            return ResponseEntity.ok(respostas);
        }
    }


}
