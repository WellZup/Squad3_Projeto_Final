package com.squad3.bemestar.repository;

import com.squad3.bemestar.domain.entity.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntasRepository extends JpaRepository<Perguntas, Long> {



}
