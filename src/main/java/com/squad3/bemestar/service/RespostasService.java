package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.repository.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostasService {
    @Autowired
    private RespostasRepository respostasRepository;


    @Autowired
    private PerguntasService perguntasService;


    public Respostas adicionaResposta(Respostas respostas){
        return respostasRepository.save(respostas);
    }

    public List<Respostas> listarRespostas() {
        return respostasRepository.findAll();
    }

    //Método para listar Repostas por id
    public Respostas listarRespostaPorId(Long id) {
        return respostasRepository.findById(id).get();
    }


}
