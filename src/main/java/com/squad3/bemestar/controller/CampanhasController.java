package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.service.CampanhasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/campanhas")
public class CampanhasController {

    @Autowired
    private CampanhasService campanhasService;

    //Endpoint para listar todas as campanhas
    @GetMapping
    public ResponseEntity<List<Campanhas>> listarCampanhas() {
        try {
        List<Campanhas> campanhas = campanhasService.listarCampanhas();
        return ResponseEntity.ok(campanhas);
        } catch (CampanhaNotFoundException e) { return ResponseEntity.notFound().build();}
        catch (Exception e) {
            System.out.println(e.getMessage());}
        return null;
    }

    //Endpoint para listar campanhas por id
    @GetMapping("/{id}")
    public ResponseEntity<Campanhas> listarCampanhasPorId(@PathVariable Long id) {
        Campanhas campanhas = campanhasService.listarCampanhasPorId(id);
        if (campanhas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(campanhas);
        }

    }

    //Endpoint para criar uma nova campanha
    @PostMapping
    public ResponseEntity<Campanhas> criarCampanha(@RequestBody Campanhas campanhas) {
        try {
            Campanhas campanhasCriada = campanhasService.criarCampanha(campanhas);
            return ResponseEntity.status(HttpStatus.CREATED).body(campanhasCriada);
        } catch (Exception e) {
            System.out.println("Erro ao criar Campanha");
        }
        return null;
    }

    //Endpoint para atualizar uma campanha
    @PutMapping
    public ResponseEntity<Campanhas> atualizarCampanha(@RequestBody Campanhas campanhas) {
        try {

        Campanhas campanhasAtualizada = campanhasService.atualizarCampanha(campanhas);
        return ResponseEntity.ok(campanhasAtualizada);
        }catch (CampanhaNotFoundException e ) { return ResponseEntity.notFound().build();}
    }

    //Endpoint para deletar uma campanha
    @DeleteMapping("/{id}")
    public void deletarCampanha(@PathVariable Long id) {
        try{
            campanhasService.deletarCampanha(id);
        ResponseEntity.noContent().build();
        }catch (CampanhaNotFoundException e) {
            ResponseEntity.notFound().build();
        }

    }


    public class CampanhaNotFoundException extends RuntimeException {
        public CampanhaNotFoundException(Long id) {
            super("Campanha com ID " + id + " não encontrada.");
        }
    }
}
