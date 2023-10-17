package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.MediaRespostasDTO;
import com.squad3.bemestar.service.MediaRespostasChartService;
import com.squad3.bemestar.service.MediaRespostasServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MediaRespostasController.class)
public class MediaRespostasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaRespostasServiceImpl mediaRespostasService;

    @MockBean
    private MediaRespostasChartService mediaRespostasChartService;

    @Test
    public void testCalcularMediaRespostas() throws Exception {
        // Mock dos dados de resposta do serviço que é:
        MediaRespostasDTO mediaRespostaDTO = new MediaRespostasDTO();
        mediaRespostaDTO.setPerguntaId(1L);
        mediaRespostaDTO.setMediaRespostas(4.5);
        mediaRespostaDTO.setNomeCampanha("Campanha de Teste");
        mediaRespostaDTO.setPerguntaTexto("Pergunta de Teste");
        Mockito.when(mediaRespostasService.calcularMediaRespostas(1L)).thenReturn(Collections.singletonList(mediaRespostaDTO));

        //Requisição HTTP GET para o endpoint desejado que é o /api/calculos/media-respostas
        mockMvc.perform(MockMvcRequestBuilders.get("/api/calculos/media-respostas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].perguntaId").value(1))
                .andExpect(jsonPath("$[0].mediaRespostas").value(4.5))
                .andExpect(jsonPath("$[0].nomeCampanha").value("Campanha de Teste"))
                .andExpect(jsonPath("$[0].perguntaTexto").value("Pergunta de Teste"));
    }

    @Test
    public void testCalcularMediaRespostasChart() throws Exception {
        // Mock dos dados de resposta do serviço que é:
        MediaRespostasDTO mediaRespostaDTO = new MediaRespostasDTO();
        mediaRespostaDTO.setPerguntaId(1L);
        mediaRespostaDTO.setMediaRespostas(4.5);
        mediaRespostaDTO.setNomeCampanha("Campanha de Teste");
        mediaRespostaDTO.setPerguntaTexto("Pergunta de Teste");
        Mockito.when(mediaRespostasService.calcularMediaRespostas(1L)).thenReturn(Collections.singletonList(mediaRespostaDTO));

        //Requisição HTTP GET para o endpoint desejado que é o /api/calculos/grafico/1 que gera o gráfico das médias.
        mockMvc.perform(MockMvcRequestBuilders.get("/api/calculos/grafico/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].perguntaId").value(1))
                .andExpect(jsonPath("$[0].mediaRespostas").value(4.5))
                .andExpect(jsonPath("$[0].nomeCampanha").value("Campanha de Teste"))
                .andExpect(jsonPath("$[0].perguntaTexto").value("Pergunta de Teste"));
    }
}

