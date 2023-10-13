package com.squad3.bemestar.domain.mapper;

import com.squad3.bemestar.domain.dto.RespostasDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.domain.entity.Usuario;

public class RespostaMapper {
    // Método de conversão de DTO para entidade
    private Respostas toEntity(RespostasDTO respostasDTO) {
        Respostas respostas = new Respostas();
        respostas.setId(respostasDTO.getId());
        respostas.setRespostaTexto(respostasDTO.getRespostaTexto());

        // Outros campos que você deseja mapear
        return respostas;
    }

    // Método de conversão de entidade para DTO
    private RespostasDTO toDTO(Respostas respostas) {
        RespostasDTO dto = new RespostasDTO();
        dto.setId(respostas.getId());
        dto.setRespostaTexto(respostas.getRespostaTexto());

        // Outros campos que você deseja mapear
        Perguntas pergunta = respostas.getPerguntas();
        if (pergunta != null) {
            dto.setPerguntaId(pergunta.getId());
            dto.setPerguntaTexto(pergunta.getPerguntaTexto());
        }

        Usuario usuarioColaborador = respostas.getUsuarioColaborador();
        if (usuarioColaborador != null) {
            dto.setUsuarioColaboradorId(usuarioColaborador.getId());
        }

        // Mapear o campo nomeCampanha
        Campanhas campanha = pergunta.getCampanhas();
        if (campanha != null) {
            dto.setNomeCampanha(campanha.getNomeCampanha());
        }

        return dto;
    }
}
