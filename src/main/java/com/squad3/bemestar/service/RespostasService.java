package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.repository.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostasService {
    @Autowired
    private RespostasRepository respostasRepository;

    public Respostas adicionaResposta(Respostas respostas){
        return respostasRepository.save(respostas);
    }

    public List<Respostas> listarRespostas() {
        return respostasRepository.findAll();
    }

    public Respostas listarRespostaPorId(Long id) {
        return respostasRepository.findById(id).get();
    }
}
