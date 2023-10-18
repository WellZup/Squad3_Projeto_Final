package com.squad3.bemestar.controller;


import com.squad3.bemestar.domain.dto.PerguntasDTO;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.exception.PerguntaCreationException;
import com.squad3.bemestar.exception.PerguntaNotFoundException;
import com.squad3.bemestar.service.PerguntasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/perguntas")

public class PerguntasController {

    @Autowired
    private PerguntasServiceImpl perguntasService;

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar todas as perguntas", description = "Listar Perguntas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma pergunta encontrada!")
    })

    //Endoint para listar todas as perguntas conforme DTO
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PerguntasDTO>> listarPerguntas() {
        List<Perguntas> perguntas = perguntasService.listarPerguntas();
        List<PerguntasDTO> perguntasDTOs = perguntas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(perguntasDTOs);
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar as perguntas por ID", description = "Listar Perguntas por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Pergunta não encontrada!")
    })

    //Endpoint para buscar pergunta por ID
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PerguntasDTO> buscarPorId(@PathVariable("id") Long id) {
        try {
            Perguntas perguntas = perguntasService.buscarPorId(id);
            if (perguntas == null) {
                throw new PerguntaNotFoundException("Pergunta não encontrada para o ID: " + id);
            }
            PerguntasDTO perguntasDTO = convertToDTO(perguntas);
            return ResponseEntity.ok(perguntasDTO);
        } catch (Exception e) {
            throw new PerguntaNotFoundException("Pergunta não encontrada para o ID: " + id);
        }
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite criar uma nova Pergunta", description = "Criar Perguntas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta criada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao criar pergunta!")
    })

    //Endpoint para uma nova pergunta
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Perguntas> adicionaPergunta(@RequestBody @Valid Perguntas perguntas) {
        // O uso da anotação @Valid aciona as validações do Bean Validation

        try {

        Perguntas novaPergunta = perguntasService.adicionaPergunta(perguntas);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);
        } catch (PerguntaCreationException e) {
            throw new PerguntaCreationException("Erro ao criar pergunta: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite deletar Pergunta", description = "Deletar Perguntas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta deletada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao deletar pergunta!")
    })

    //Endpoint para deletar pergunta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPergunta(@PathVariable @Positive Long id) {
        try {

            perguntasService.deletarPergunta(id);
            return ResponseEntity.noContent().build();
        } catch (PerguntaNotFoundException e) {
            throw new PerguntaNotFoundException("Pergunta não encontrada para o ID: " + id);
        }
    }


    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar todas as Perguntas de uma determinada Campanha (Pesquisa)",
            description = "Listar Perguntas por Campanha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma pergunta encontrada!")
    })

    //Endpoint para listar todas as perguntas de uma campanha específica por campanhaID
    @GetMapping("/campanhas/{campanhaId}")
    public ResponseEntity<List<PerguntasDTO>> listarPerguntasPorCampanha(@PathVariable Long campanhaId) {
        List<PerguntasDTO> perguntas = perguntasService.listarPerguntasPorCampanha(campanhaId);
        if (perguntas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perguntas);
    }


    private PerguntasDTO convertToDTO(Perguntas perguntas) {
        PerguntasDTO perguntasDTO = new PerguntasDTO();
        perguntasDTO.setId(perguntas.getId());
        perguntasDTO.setPerguntaTexto(perguntas.getPerguntaTexto());
        perguntasDTO.setTipo(perguntas.getTipo());

        if (perguntas.getCampanhas() != null) {
            perguntasDTO.setCampanhaId(perguntas.getCampanhas().getId());
        }

        return perguntasDTO;
    }




}