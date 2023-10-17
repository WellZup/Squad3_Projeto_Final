package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.CampanhasDTO;
import com.squad3.bemestar.domain.entity.Campanhas;
import com.squad3.bemestar.exception.CampanhaException;
import com.squad3.bemestar.exception.CampanhaNotFoundException;
import com.squad3.bemestar.service.CampanhasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.squad3.bemestar.domain.mapper.CampanhaMapper.convertToEntity;

@RestController
@RequestMapping("api/campanhas")
public class CampanhasController {

    @Autowired
    private CampanhasServiceImpl campanhasService;

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar todas as Campanhas (pesquisas)", description = "Listar Campanhas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma campanha encontrada!")
    })

    //Endpoint para listar todas as campanhas
    @GetMapping
    public ResponseEntity<List<CampanhasDTO>> listarCampanhas() {
        try {
            List<Campanhas> campanhas = campanhasService.listarCampanhas();
            List<CampanhasDTO> campanhasDTOS = campanhas.stream()
                    .map(this::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(campanhasDTOS);
        } catch (CampanhaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar as Campanhas (pesquisas) por ID", description = "Listar Campanhas por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Campanha não encontrada!")
    })

    //Endpoint para listar campanhas por id
    @GetMapping("/{id}")
    public ResponseEntity<CampanhasDTO> listarCampanhasPorId(@PathVariable Long id) {
        try {
            Campanhas campanhas = campanhasService.listarCampanhasPorId(id);
            if (campanhas == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                CampanhasDTO campanhasDTO = convertToDTO(campanhas);
                return ResponseEntity.ok(campanhasDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite criar uma nova Campanha (pesquisa)", description = "Criar Campanha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Camapanha criada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao criar campanha!")
    })

    //Endpoint para criar uma nova campanha
    @PostMapping
    public ResponseEntity<CampanhasDTO> criarCampanha(@Valid @RequestBody CampanhasDTO campanhasDTO) {
        try {
            Campanhas campanhas = convertToEntity(campanhasDTO);
            Campanhas campanhasCriada = campanhasService.criarCampanha(campanhas);
            CampanhasDTO campanhasCriadaDTO = convertToDTO(campanhasCriada);
            return ResponseEntity.status(HttpStatus.CREATED).body(campanhasCriadaDTO);
        } catch (CampanhaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite Atualizar uma Campanha (pesquisa)", description = "Atualizar Campanha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Camapanha atualizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao atualizar campanha!")
    })

    //Endpoint para atualizar uma campanha
    @PutMapping("/{id}")
    public ResponseEntity<CampanhasDTO> atualizarCampanha(@PathVariable Long id,
                                                          @RequestBody CampanhasDTO campanhasDTO) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Campanhas campanhas = convertToEntity(campanhasDTO);
            Campanhas campanhasAtualizada = campanhasService.atualizarCampanha(id, campanhas);
            if (campanhasAtualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                CampanhasDTO campanhasAtualizadaDTO = convertToDTO(campanhasAtualizada);
                return ResponseEntity.ok(campanhasAtualizadaDTO);
            }

        } catch (CampanhaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite Deletar Campanha (pesquisa)", description = "Deletar Campanha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Camapanha deletada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Erro ao deletar campanha!")
    })

    //Endpoint para deletar uma campanha
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            boolean deletado = campanhasService.deletarCampanha(id);
            if (deletado) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Métodos de conversão (convertToDTO e convertToEntity) para converter entre a entidade e o DTO.

    private CampanhasDTO convertToDTO(Campanhas campanhas) {
        CampanhasDTO campanhasDTO = new CampanhasDTO();
        campanhasDTO.setId(campanhas.getId());
        campanhasDTO.setNomeCampanha(campanhas.getNomeCampanha());
        campanhasDTO.setDataInicio(campanhas.getDataInicio());
        campanhasDTO.setDataFim(campanhas.getDataFim());
        return campanhasDTO;
    }



}
