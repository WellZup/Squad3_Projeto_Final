package com.squad3.bemestar.service;

import com.squad3.bemestar.domain.dto.MediaRespostasDTO;
import com.squad3.bemestar.repository.MediaRepostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaRespostasServiceImpl implements MediaRespostasService{

    @Autowired
    private MediaRepostasRepository mediaRepostasRepository;

    public List<MediaRespostasDTO> calcularMediaRespostas(Long campanhaId) {
        List<Object[]> result = mediaRepostasRepository.calcularMediaRespostas(campanhaId);

        // Transformar os resultados em instâncias de MediaRespostasDTO e retorne a lista
        List<MediaRespostasDTO> medias = new ArrayList<>();
        for (Object[] row : result) {
            MediaRespostasDTO mediaDTO = new MediaRespostasDTO();
            mediaDTO.setPerguntaId((Long) row[0]);
            mediaDTO.setPerguntaTexto((String) row[1]);
            mediaDTO.setCampanhaId((Long) row[2]);
            mediaDTO.setNomeCampanha((String) row[3]);
            mediaDTO.setMediaRespostas((Double) row[4]);
            medias.add(mediaDTO);
        }
        return medias;
    }
}
