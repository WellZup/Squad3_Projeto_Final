package com.squad3.bemestar.controller;

import com.squad3.bemestar.domain.dto.MediaRespostasDTO;
import com.squad3.bemestar.service.MediaRespostasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calculos")
public class MediaRespostasController {

    @Autowired
    private MediaRespostasService mediaRespostasService;

    @GetMapping("/media-respostas")
    public ResponseEntity<List<MediaRespostasDTO>> calcularMediaRespostas() {
        List<MediaRespostasDTO> medias = mediaRespostasService.calcularMediaRespostas();
        return ResponseEntity.ok(medias);
    }

}
