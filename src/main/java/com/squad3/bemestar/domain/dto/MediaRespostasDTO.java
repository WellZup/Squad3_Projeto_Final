package com.squad3.bemestar.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MediaRespostasDTO {

    private Long perguntaId;
    private String perguntaTexto;
    private Long campanhaId;
    private String nomeCampanha;
    private double mediaRespostas;
}
