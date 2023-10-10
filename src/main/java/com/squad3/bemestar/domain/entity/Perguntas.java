package com.squad3.bemestar.domain.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

//@NotNull - para garantir que o campo perguntaTexto não seja nulo.
//@NotBlank - para garantir que o campo perguntaTexto não esteja em branco.
//@Size - para especificar o tamanho mínimo e máximo permitido para o campo perguntaTexto.

    @NotNull(message = "O campo 'perguntaTexto' não pode ser nulo.")
    @NotBlank(message = "O campo 'perguntaTexto' não pode estar em branco.")
    @Size(min = 5, max = 100, message = "O campo 'perguntaTexto' deve ter entre 5 e 100 caracteres.")
    @Column(name = "pergunta_texto")
    private String perguntaTexto;

    @Column(name = "tipo")
    private String tipo;

    //Relacionamento @ManyToOne com Campanha, usando a coluna campanha_id como chave estrangeira.
    //Cada campanha pode ter sua própria lista de perguntas associadas a ela.
    // Quando você cria uma campanha e adiciona perguntas a ela,
    // essas perguntas estarão relacionadas a essa campanha específica.

//    @JsonBackReference //Propriedade para indicar que esta é a extremidade inversa do relacionamento
    @ManyToOne
    @JoinColumn(name = "campanha_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Campanhas campanhas;

    @OneToMany(mappedBy = "perguntas", cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Respostas> respostas;
    //Cada pergunta pode ter várias respostas associadas a ela




}
