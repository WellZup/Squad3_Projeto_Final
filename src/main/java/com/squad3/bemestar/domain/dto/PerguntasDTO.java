package com.squad3.bemestar.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(Include.NON_NULL) os campos com valores nulos (null)
//sejam ignorados na serialização para JSON.
public class PerguntasDTO {

    private Long id;
    private String perguntaTexto;
    private String tipo;
    private Long campanhaId;
    private String nomeCampanha;


}

