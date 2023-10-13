package com.squad3.bemestar.domain.mapper;

import com.squad3.bemestar.domain.dto.CampanhasDTO;
import com.squad3.bemestar.domain.entity.Campanhas;

public class CampanhaMapper {
    //Métodos de conversão (convertToDTO e convertToEntity) para converter entre a entidade e o DTO.

    public static CampanhasDTO convertToDTO(Campanhas campanhas) {
        CampanhasDTO campanhasDTO = new CampanhasDTO();
        campanhasDTO.setId(campanhas.getId());
        campanhasDTO.setNomeCampanha(campanhas.getNomeCampanha());
        campanhasDTO.setDataInicio(campanhas.getDataInicio());
        campanhasDTO.setDataFim(campanhas.getDataFim());
        return campanhasDTO;
    }

    public static Campanhas convertToEntity(CampanhasDTO campanhasDTO) {
        Campanhas campanhas = new Campanhas();
        campanhas.setId(campanhasDTO.getId());
        campanhas.setNomeCampanha(campanhasDTO.getNomeCampanha());
        campanhas.setDataInicio(campanhasDTO.getDataInicio());
        campanhas.setDataFim(campanhasDTO.getDataFim());
        return campanhas;
    }

}
