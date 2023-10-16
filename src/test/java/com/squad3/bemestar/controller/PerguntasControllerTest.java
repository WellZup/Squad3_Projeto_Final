package com.squad3.bemestar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.service.PerguntaService;
import com.squad3.bemestar.service.PerguntasServiceImpl;
import com.squad3.bemestar.service.RespostasServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PerguntasController.class)
class PerguntasControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PerguntasController perguntasController;

    @MockBean
    PerguntasServiceImpl perguntaService;


    @BeforeEach
    public void setup() {

        RestAssuredMockMvc.standaloneSetup(this.perguntasController);


    }

    @DisplayName("Adicionar nova Pergunta")
    @Test
    public void testAdicionaPergunta() throws Exception {
        Perguntas perguntas = new Perguntas();
        perguntas.setId(1L);
        perguntas.setPerguntaTexto("De 1 a 5, digite como está seu dia hoje:");
        perguntas.setTipo("indice");


        Mockito.when(perguntaService.adicionaPergunta(any())).thenReturn(perguntas);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/perguntas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"perguntaTexto\": \"de um a 5\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.perguntaTexto").value("De 1 a 5, digite como está seu dia hoje:"))
                .andExpect(jsonPath("$.tipo").value("indice"));


    }

    @DisplayName("Buscar todas as Perguntas")
    @Test
    public void testBuscarTodasPerguntas() throws Exception {
        List<Perguntas> perguntasList = new ArrayList<>();
        Perguntas pergunta1 = new Perguntas();
        pergunta1.setId(1L);
        pergunta1.setPerguntaTexto("De 1 a 5, digite como está seu dia hoje:");
        pergunta1.setTipo("indice");

        Perguntas pergunta2 = new Perguntas();
        pergunta2.setId(2L);
        pergunta2.setPerguntaTexto("De 1 a 5, qual o seu nível de cansaço?");


        perguntasList.add(pergunta1);
        perguntasList.add(pergunta2);

        Mockito.when(perguntaService.listarPerguntas()).thenReturn(perguntasList);

        mockMvc.perform(get("/api/perguntas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].perguntaTexto").value("De 1 a 5, digite como está seu dia hoje:"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].perguntaTexto").value("De 1 a 5, qual o seu nível de cansaço?"));
    }

    @DisplayName("ID da pergunta não encontrado")
    @Test
    public void perguntaNaoEncontrada() throws Exception {
        mockMvc.perform(get("/api/perguntas/{id}", 1L))
                .andExpect(status().isNotFound());
    }


}