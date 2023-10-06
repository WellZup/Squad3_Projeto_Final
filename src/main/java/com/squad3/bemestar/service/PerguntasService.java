package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.dto.PerguntasDTO;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.repository.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerguntasService {

    @Autowired
    private PerguntasRepository perguntasRepository;

    public Perguntas adicionaPergunta(Perguntas perguntas) {
        return perguntasRepository.save(perguntas);
    }

    public List<Perguntas> listarPerguntas() {
        return perguntasRepository.findAll();
    }

    public Perguntas buscarPorId(Long id) {
        return perguntasRepository.findById(id).orElseThrow();
    }

    public Perguntas atualizarPerguntas(Long id) {
        Perguntas perguntas = buscarPorId(id);
        perguntas.setPerguntaTexto(perguntas.getPerguntaTexto());
        perguntas.setTipo(perguntas.getTipo());

        return perguntasRepository.save(perguntas);
    }

    public void deletarPergunta(Long id) {
        Perguntas perguntas = buscarPorId(id);
        perguntasRepository.delete(perguntas);
    }

    //Método para listar todas as perguntas de uma campanha especifica pelo ID
    public List<PerguntasDTO> listarPerguntasPorCampanha(Long campanhasId) {
        List<Perguntas> perguntas = perguntasRepository.findByCampanhasId(campanhasId);
        return convertToDTOList(perguntas);
    }

    private List<PerguntasDTO> convertToDTOList(List<Perguntas> perguntas) {
        return perguntas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PerguntasDTO convertToDTO(Perguntas pergunta) {
        PerguntasDTO dto = new PerguntasDTO();
        dto.setId(pergunta.getId());
        dto.setPerguntaTexto(pergunta.getPerguntaTexto());
        dto.setTipo(pergunta.getTipo());

        // Definir campanhaId, se a pergunta tiver uma referência à campanha.
        if (pergunta.getCampanhas() != null) {
            dto.setCampanhaId(pergunta.getCampanhas().getId()); // Buscar campanhaId
            dto.setNomeCampanha(pergunta.getCampanhas().getNomeCampanha()); // Buscar nomeCampanha

        }

        return dto;
    }



}
