package com.squad3.bemestar.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campanhas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campanhas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_campanha")
    private String nomeCampanha;

    @Column(name = "data_inicio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    // Mapeamento para a tabela de associação
    //O relacionamento @OneToMany é configurado com CascadeType.ALL,
    // o que significa que quando você persiste ou remove uma campanha,
    // as perguntas associadas também serão afetadas.
    // O orphanRemoval = true garante que as perguntas sejam removidas quando
    // não estão mais associadas a uma campanha.
   //A opção mappedBy na associação @OneToMany.
    // Isso indica que a propriedade perguntas na classe Campanha está mapeada
    // para o relacionamento campanha na classe Pergunta.
    @OneToMany(mappedBy = "campanhas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perguntas> perguntas;

}
