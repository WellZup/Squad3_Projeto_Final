package com.squad3.bemestar.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "perguntas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pergunta_texto")
    private String perguntaTexto;

    @Column(name = "tipo")
    private String tipo;

    //Relacionamento @ManyToOne com Campanha, usando a coluna campanha_id como chave estrangeira.
    //Cada campanha pode ter sua própria lista de perguntas associadas a ela.
    // Quando você cria uma campanha e adiciona perguntas a ela,
    // essas perguntas estarão relacionadas a essa campanha específica.
    @ManyToOne
    @JoinColumn(name = "campanha_id")
    private Campanhas campanhas;

//    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
//    private List<Respostas> respostas;
//    //Cada pergunta pode ter várias respostas associadas a ela




}
