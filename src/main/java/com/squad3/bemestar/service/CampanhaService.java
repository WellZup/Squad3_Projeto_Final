package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.entity.Campanhas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaService {
    List<Campanhas> listarCampanhas();
    Campanhas listarCampanhasPorId(Long id);
    Campanhas criarCampanha(Campanhas campanhas);
    Campanhas atualizarCampanha(Long id, Campanhas campanhas);
    boolean deletarCampanha(Long id);
}
