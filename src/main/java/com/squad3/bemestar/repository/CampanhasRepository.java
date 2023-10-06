package com.squad3.bemestar.repository;

import com.squad3.bemestar.domain.entity.Campanhas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhasRepository extends JpaRepository<Campanhas, Long> {

}
