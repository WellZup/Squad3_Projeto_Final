package com.squad3.bemestar.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Cada usuário pode ter várias respostas associadas a ele.
    // Um relacionamento @OneToMany na classe Usuario que se refere às respostas
    // fornecidas por esse usuário.
    @OneToMany(mappedBy = "usuarioColaborador", cascade = CascadeType.ALL)
    private List<Respostas> respostas;


}
