package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.dto.MediaRespostasDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MediaRespostasService {
    List<MediaRespostasDTO> calcularMediaRespostas(Long campanhaId);

}
