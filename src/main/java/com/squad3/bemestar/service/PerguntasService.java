package com.squad3.bemestar.service;

import com.squad3.bemestar.controller.PerguntasController;
import com.squad3.bemestar.domain.dto.PerguntaDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.repository.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PerguntasService {

    @Autowired
    private PerguntasRepository perguntasRepository;

    public Perguntas adicionaPergunta(Perguntas perguntas){
        return perguntasRepository.save(perguntas);
    }


//    public List<Perguntas> listarPerguntas() {
//        return perguntasRepository.findAll();
//    }
}
