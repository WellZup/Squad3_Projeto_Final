package com.squad3.bemestar.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Respostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resposta_texto")
    private String respostaTexto;


    //Associar várias respostas a cada usuário
    // e rastrear qual usuário forneceu cada resposta.
    //Cada resposta estará vinculada a uma pergunta,
    // e ao mesmo tempo, cada resposta estará vinculada a um usuário específico que a forneceu.
    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private Perguntas perguntas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioColaborador;
}
