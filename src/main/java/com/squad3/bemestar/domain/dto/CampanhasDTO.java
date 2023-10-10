package com.squad3.bemestar.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(Include.NON_NULL) os campos com valores nulos (null)
//sejam ignorados na serialização para JSON.
public class CampanhasDTO {

    private Long id;

    @NotBlank(message = "O nome da campanha não pode estar em branco.")
    private String nomeCampanha;
    @NotNull(message = "A data de início não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @NotNull(message = "A data de fim não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

//@NotBlank - garante que o campo nomeCampanha não pode estar em branco e exibe a mensagem de erro se estiver em branco.
//@NotNull - garante que os campos dataInicio e dataFim não podem ser nulos e exibe mensagens de erro se forem nulos.
}
