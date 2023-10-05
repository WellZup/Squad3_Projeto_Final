package com.squad3.bemestar.repository;

import com.squad3.bemestar.domain.entity.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntasRepository extends JpaRepository<Perguntas, Long> {

    //Método findByCampanhaId para consultar as perguntas por ID da campanha.
    List<Perguntas> findByCampanhasId(Long campanhasId);
}
