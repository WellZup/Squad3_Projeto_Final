package com.squad3.bemestar.domain.dto;

import lombok.Data;

@Data
public class PerguntaDTO {

    public PerguntaDTO(String texto) {
        this.texto = texto;
    }
    public PerguntaDTO(){
        super();
    }

    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
