package com.squad3.bemestar.domain.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerguntaDTO {

    private Long id;
    private String perguntaTexto;
    private String tipo;
    private String nomeCampanha;
}
