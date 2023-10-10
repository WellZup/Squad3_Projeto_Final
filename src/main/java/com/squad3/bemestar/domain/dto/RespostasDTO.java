package com.squad3.bemestar.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(Include.NON_NULL) os campos com valores nulos (null)
//sejam ignorados na serialização para JSON.
public class RespostasDTO {

    private Long id;
    private Double respostaTexto;

    // Mapeamento para buscar dados da pergunta
    private Long perguntaId;
    private String perguntaTexto;

    // Mapeamento para buscar dados do usuário colaborador
    private Long usuarioColaboradorId;

    // Mapeamento para buscar o nome da campanha
    private String nomeCampanha;

}
