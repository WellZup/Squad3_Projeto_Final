package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.MediaRespostasDTO;
import com.squad3.bemestar.service.MediaRespostasChartService;
import com.squad3.bemestar.service.MediaRespostasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/calculos")
public class MediaRespostasController {

    @Autowired
    private MediaRespostasServiceImpl mediaRespostasService;

    @Autowired
    private MediaRespostasChartService mediaRespostasChartService;

    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar a média de todas as Respostas", description = "Listar Média Respostas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Média não encontrada!")
    })

    @GetMapping("/media-respostas/{campanhaId}")
    public ResponseEntity<List<MediaRespostasDTO>> calcularMediaRespostas(@PathVariable Long campanhaId) {
        List<MediaRespostasDTO> medias = mediaRespostasService.calcularMediaRespostas(campanhaId);
        return ResponseEntity.ok(medias);
    }


    //Anotações para documentação no Swegger
    @Operation(summary = "Permite listar média de todas as Respostas através de gráfico",
            description = "Listar Respostas no Gráfico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhuma resposta encontrada!")
    })
    @GetMapping("/grafico/{campanhaId}")
    public ResponseEntity<List<MediaRespostasDTO>> calcularMediaRespostasChart(@PathVariable Long campanhaId) throws IOException {
        List<MediaRespostasDTO> medias = mediaRespostasService.calcularMediaRespostas(campanhaId);

        // Aqui, você pode passar os dados obtidos para o serviço de geração de gráficos
        List<Long> perguntasId = new ArrayList<>();
        List<Double> mediasRespostas = new ArrayList<>();
        List<String> nomesCampanha = new ArrayList<>();
        String nomeCampanha = "";
        List<String> perguntaTexto = new ArrayList<>();

        for (MediaRespostasDTO media : medias) {
            perguntasId.add(media.getPerguntaId());
            mediasRespostas.add(media.getMediaRespostas());
            nomesCampanha.add(media.getNomeCampanha());
            nomeCampanha = media.getNomeCampanha();
            perguntaTexto.add(media.getPerguntaTexto());
        }


        // Agora você pode chamar o serviço de geração de gráficos
        String nomeArquivoBase = "";
        mediaRespostasChartService.gerarGrafico(perguntasId, mediasRespostas, nomesCampanha, nomeCampanha, perguntaTexto, nomeArquivoBase);


        return ResponseEntity.ok(medias);
    }

}
