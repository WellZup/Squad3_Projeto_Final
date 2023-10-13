package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.dto.RespostasDTO;
import com.squad3.bemestar.domain.entity.Respostas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaService {
    Respostas adicionaResposta(Respostas respostas);
    List<Respostas> listarRespostas();
    List<RespostasDTO> listarRespostasDTO();
    RespostasDTO listarRespostaPorId(Long id);
}
