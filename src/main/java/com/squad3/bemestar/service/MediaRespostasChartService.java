package com.squad3.bemestar.service;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MediaRespostasChartService {


    public void gerarGrafico(List<Long> perguntasId, List<Double> medias,
                             List<String> nomesCampanha, String nomeCampanha,
                             List<String> perguntaTexto, String nomeArquivoBase) throws IOException {
        // Criar um gráfico de barras
        CategoryChart chart = new CategoryChartBuilder()
                .width(1000)
                .height(1000)
                .title("Média de Respostas por Pergunta - " + nomeCampanha)
                .xAxisTitle("Pergunta ID")
                .yAxisTitle("Média")
                .theme(Styler.ChartTheme.Matlab)
                .build();

        chart.getStyler().setHasAnnotations(true);

        // Criar uma lista de cores personalizadas para as barras
        List<Color> customColors = Arrays.asList(
                Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PINK);

        // Adicionar séries de dados ao gráfico - Gráfico de barra simples
        for (int i = 0; i < perguntasId.size(); i++) {
            String descricao = "ID " + perguntasId.get(i) + ": " + perguntaTexto.get(i);
            chart.addSeries(descricao, new ArrayList<>(Arrays.asList(" ")),
                            new ArrayList<>(Arrays.asList(medias.get(i))))
                    .setFillColor(customColors.get(i)); // Definir a cor da barra
        }

        // Adicionar descrições ou legendas abaixo do gráfico
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);

        //Obter data e hora atual para gerar arquivo PNG
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HHmmss");
        String dataHora = dateFormat.format(new Date());

        // Concatenar a data e hora ao nome do arquivo
        nomeArquivoBase = nomeCampanha + "-" + dataHora + ".png";

        // Salvar o gráfico em um arquivo PNG
        BitmapEncoder.saveBitmap(chart, nomeArquivoBase, BitmapEncoder.BitmapFormat.PNG);
    }

}