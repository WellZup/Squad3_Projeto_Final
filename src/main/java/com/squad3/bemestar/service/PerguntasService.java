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

    public List<Perguntas> listarPerguntas() {return  perguntasRepository.findAll();}

    public Perguntas buscarPorId(Long id) {return perguntasRepository.findById(id).orElseThrow();}

    public Perguntas atualizarPerguntas(Long id) {
        Perguntas perguntas = buscarPorId(id);
        perguntas.setPerguntaTexto(perguntas.getPerguntaTexto());
        perguntas.setTipo(perguntas.getTipo());

        return  perguntasRepository.save(perguntas);
    }

    public void deletarPergunta(Long id) {
        Perguntas perguntas = buscarPorId(id);
        perguntasRepository.delete(perguntas);
    }

}
