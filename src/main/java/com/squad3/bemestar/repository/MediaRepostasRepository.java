package com.squad3.bemestar.repository;

import com.squad3.bemestar.domain.entity.Respostas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepostasRepository extends JpaRepository<Respostas, Long> {

    @Query("SELECT r.perguntas.id AS perguntaId, r.perguntas.perguntaTexto AS perguntaTexto, " +
            "r.perguntas.campanhas.id AS campanhaId, r.perguntas.campanhas.nomeCampanha AS nomeCampanha, " +
            "AVG(r.respostaTexto) AS mediaRespostas " +
            "FROM Respostas r " +
            "WHERE r.perguntas.campanhas.id = :campanhaId " + // Adicione esta condição
            "GROUP BY r.perguntas.id, r.perguntas.perguntaTexto, r.perguntas.campanhas.id, r.perguntas.campanhas.nomeCampanha")
    List<Object[]> calcularMediaRespostas(@Param("campanhaId") Long campanhaId);
}

