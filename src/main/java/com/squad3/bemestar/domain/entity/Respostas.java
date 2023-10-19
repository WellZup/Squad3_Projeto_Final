package com.squad3.bemestar.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Respostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "resposta_texto")
    private double respostaTexto;

    //Associar várias respostas a cada usuário
    // e rastrear qual usuário forneceu cada resposta.
    //Cada resposta estará vinculada a uma pergunta,
    // e ao mesmo tempo, cada resposta estará vinculada a um usuário específico que a forneceu.
    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    @JsonIgnoreProperties({"perguntaTexto", "tipo", "campanhas","respostas"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Perguntas perguntas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"respostas"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Usuario usuarioColaborador;


}
