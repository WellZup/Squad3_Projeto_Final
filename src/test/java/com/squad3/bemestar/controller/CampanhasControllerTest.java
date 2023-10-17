package com.squad3.bemestar.controller;


import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.service.CampanhasServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampanhasController.class)
public class CampanhasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampanhasServiceImpl campanhasService;

    @Test
    public void listarTodasCampanhas() throws Exception {
        Campanhas campanha1 = new Campanhas();
        campanha1.setId(1L);
        campanha1.setNomeCampanha("Campanha 1");

        Campanhas campanha2 = new Campanhas();
        campanha2.setId(2L);
        campanha2.setNomeCampanha("Campanha 2");

        List<Campanhas> campanhasList = Arrays.asList(campanha1, campanha2);

        Mockito.when(campanhasService.listarCampanhas()).thenReturn(campanhasList);

        mockMvc.perform(get("/api/campanhas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeCampanha").value("Campanha 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nomeCampanha").value("Campanha 2"));
    }

    @Test
    public void listarCampanhaPorId() throws Exception {
        Campanhas campanha = new Campanhas();
        campanha.setId(1L);
        campanha.setNomeCampanha("Campanha 1");

        Mockito.when(campanhasService.listarCampanhasPorId(1L)).thenReturn(campanha);

        mockMvc.perform(get("/api/campanhas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeCampanha").value("Campanha 1"));
    }



    @Test
    public void deletarCampanha() throws Exception {
        Mockito.when(campanhasService.deletarCampanha(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/campanhas/1"))
                .andExpect(status().isNoContent());
    }

}
