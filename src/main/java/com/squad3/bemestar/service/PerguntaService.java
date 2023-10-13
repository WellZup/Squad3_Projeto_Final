package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.entity.Perguntas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaService {
    Perguntas adicionaPergunta(Perguntas perguntas);
    List<Perguntas> listarPerguntas();
    Perguntas buscarPorId(Long id);
    Perguntas atualizarPerguntas(Long id);
    public void deletarPergunta(Long id);
}
