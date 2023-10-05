package com.squad3.bemestar.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "perguntas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @JsonBackReference //Propriedade para indicar que esta é a extremidade inversa do relacionamento
    @ManyToOne
    @JoinColumn(name = "campanha_id")
    private Campanhas campanhas;

//    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
//    private List<Respostas> respostas;
//    //Cada pergunta pode ter várias respostas associadas a ela




}
