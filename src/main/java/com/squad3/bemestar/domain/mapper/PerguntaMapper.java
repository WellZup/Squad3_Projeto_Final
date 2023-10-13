package com.squad3.bemestar.domain.mapper;

import com.squad3.bemestar.domain.dto.PerguntasDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;

public class PerguntaMapper {


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


    private Perguntas convertToEntity(PerguntasDTO perguntaDTO) {
        Perguntas pergunta = new Perguntas();
        pergunta.setId(perguntaDTO.getId());
        pergunta.setPerguntaTexto(perguntaDTO.getPerguntaTexto());
        pergunta.setTipo(perguntaDTO.getTipo());

        if (perguntaDTO.getCampanhaId() != null) {
            Campanhas campanha = new Campanhas();
            campanha.setId(perguntaDTO.getCampanhaId());
//            pergunta.setCampanhas(campanha);//Associar campanha à pergunta
        }
        return pergunta;
    }
}
