package com.squad3.bemestar.service;



import com.squad3.bemestar.controller.CampanhasController;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.repository.CampanhasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



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
    public Campanhas atualizarCampanha(Long id, Campanhas campanhas) {
        Optional<Campanhas> campanhaExistente = campanhasRepository.findById(id);

        if (campanhaExistente.isPresent()) {
            Campanhas campanhaAtualizada = campanhaExistente.get();

            // Atualizar apenas os campos desejados da campanha atualizada
            campanhaAtualizada.setNomeCampanha(campanhas.getNomeCampanha());
            campanhaAtualizada.setDataInicio(campanhas.getDataInicio());
            campanhaAtualizada.setDataFim(campanhas.getDataFim());

            // Salvar a campanha atualizada no banco de dados
            return campanhasRepository.save(campanhaAtualizada);
        } else {

            throw new CampanhasController.CampanhaNotFoundException(id);
        }
    }


    //Método para deletar uma campanha
    public boolean deletarCampanha(Long id) {
        campanhasRepository.deleteById(id);
        return false;
    }


}
