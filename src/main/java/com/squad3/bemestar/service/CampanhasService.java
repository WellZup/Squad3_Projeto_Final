package com.squad3.bemestar.service;


import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.repository.CampanhasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhasService {

    @Autowired
    private CampanhasRepository campanhasRepository;

    //Método para listar todas as campanhas
    public List<Campanhas> listarCampanhas() {
        return campanhasRepository.findAll();
    }

    //Método para listar campanhas por id
    public Campanhas listarCampanhasPorId(Long id) {
        return campanhasRepository.findById(id).get();
    }

    //Método para criar uma nova campanha
    public Campanhas criarCampanha(Campanhas campanhas) {
        return campanhasRepository.save(campanhas);
    }

    //Método para atualizar uma campanha
    public Campanhas atualizarCampanha(Campanhas campanhas) {
        return campanhasRepository.save(campanhas);
    }

    //Método para deletar uma campanha
    public void deletarCampanha(Long id) {
        campanhasRepository.deleteById(id);
    }


}
