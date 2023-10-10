package com.squad3.bemestar.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "resposta_texto")
    private double respostaTexto;
<<<<<<< HEAD
=======

>>>>>>> 44b707683af5e9cecaf83d1b857a954274449b88

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
