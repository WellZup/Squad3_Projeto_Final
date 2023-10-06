package com.squad3.bemestar.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(Include.NON_NULL) os campos com valores nulos (null)
//sejam ignorados na serialização para JSON.
public class CampanhasDTO {

    private Long id;
    private String nomeCampanha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
}
