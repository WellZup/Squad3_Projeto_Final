package com.squad3.bemestar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@OpenAPIDefinition(info = @Info(title = "API para Gerenciar o Bem-estar de Colaboradores", version = "1",
        description = "API para gerenciar o nível de estresse dos desenvolvedores em seus projetos"))
public class BemestarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BemestarApplication.class, args);
    }

}
