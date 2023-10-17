package com.squad3.bemestar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squad3.bemestar.domain.dto.RespostasDTO;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.service.RespostasServiceImpl;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Optional;


@WebMvcTest(RespostasController.class)

class RespostasControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RespostasController respostasController;

    @MockBean
    RespostasServiceImpl respostaService;


    @BeforeEach
    public void setup() {

        RestAssuredMockMvc.standaloneSetup(this.respostasController);


    }

    @DisplayName("Id da resposta não encontrado")
    @Test
    public void respostaNaoEncontrada() throws Exception {
        mockMvc.perform(get("/api/respostas/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Adicionar Resposta")
    @Test
    public void testAdicionaResposta() throws Exception {
        Respostas respostas = new Respostas();
        respostas.setId(1L);
        respostas.setRespostaTexto(5);


        Mockito.when(respostaService.adicionaResposta(any())).thenReturn(respostas);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/respostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"respostaTexto\": \"5\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.respostaTexto").value(5));


    }


    @DisplayName("Buscar respostas por ID")
    @Test
    public void buscarRespostaPorId() throws Exception {
        RespostasDTO respostasDTO = new RespostasDTO();
        respostasDTO.setId(1L);
        respostasDTO.setRespostaTexto(5.0);

        Mockito.when(respostaService.listarRespostaPorId(1L)).thenReturn(respostasDTO);

        mockMvc.perform(get("/api/respostas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.respostaTexto").value(5.0));
    }



    @DisplayName("Buscar todas as respostas")
    @Test
    public void testListarTodasRespostas() throws Exception {
        List<Respostas> respostaList = new ArrayList<>();
        Respostas resposta1 = new Respostas();
        resposta1.setId(1L);
        resposta1.setRespostaTexto(2);

        Respostas resposta2 = new Respostas();
        resposta2.setId(2L);
        resposta2.setRespostaTexto(5);


        respostaList.add(resposta1);
        respostaList.add(resposta2);

        Mockito.when(respostaService.listarRespostas()).thenReturn(respostaList);

        mockMvc.perform(get("/api/respostas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].respostaTexto").value(2))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].respostaTexto").value(5));
    }
}