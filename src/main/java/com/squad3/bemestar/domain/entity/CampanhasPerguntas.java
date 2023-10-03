//package com.squad3.bemestar.domain.entity;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "campanhas_perguntas")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class CampanhasPerguntas {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "campanha_id")
//    private Campanhas campanhas;
//
//    @ManyToOne
//    @JoinColumn(name = "perguntas_id")
//    private Perguntas perguntas;
//}
