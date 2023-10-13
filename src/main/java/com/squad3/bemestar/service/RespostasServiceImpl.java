package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.dto.RespostasDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.domain.entity.Perguntas;
import com.squad3.bemestar.domain.entity.Respostas;
import com.squad3.bemestar.domain.entity.Usuario;
import com.squad3.bemestar.repository.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostasServiceImpl implements RespostaService{

    @Autowired
    private RespostasRepository respostasRepository;


    //Método para adicionar uma resposta
    public Respostas adicionaResposta(Respostas respostas) {
        return respostasRepository.save(respostas);

    }

    //Método para listar todas as respostas
    public List<Respostas> listarRespostas() {
        return respostasRepository.findAll();
    }

    // Método para listar todas as respostas como DTOs
    public List<RespostasDTO> listarRespostasDTO() {
        List<Respostas> respostas = respostasRepository.findAll();

        // Mapear as respostas para RespostasDTO com dados relacionados
        List<RespostasDTO> respostasDTO = respostas.stream()
                .map(resposta -> {
                    RespostasDTO dto = new RespostasDTO();
                    dto.setId(resposta.getId());
                    dto.setRespostaTexto(resposta.getRespostaTexto());

                    // Mapear os dados da pergunta
                    Perguntas perguntas = resposta.getPerguntas();
                    dto.setPerguntaId(perguntas.getId());
                    dto.setPerguntaTexto(perguntas.getPerguntaTexto());

                    // Mapear os dados do usuário colaborador
                    Usuario usuario = resposta.getUsuarioColaborador();
                    dto.setUsuarioColaboradorId(usuario.getId());

                    // Mapear o nome da campanha
                    Perguntas pergunta = resposta.getPerguntas();
                    if (pergunta != null) { // Verificar se a pergunta não é nula
                        Campanhas campanha = pergunta.getCampanhas();
                        if (campanha != null) { // Verificar se a campanha não é nula
                            dto.setNomeCampanha(campanha.getNomeCampanha());
                        }
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return respostasDTO;
    }


    // Método para listar uma resposta por ID como DTO
    public RespostasDTO listarRespostaPorId(Long id) {
        Respostas respostas = respostasRepository.findById(id).orElse(null);
        if (respostas == null) {
            return null;
        }

        return toDTO(respostas);
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

