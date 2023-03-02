package com.ivo.tools;

import com.ivo.core.Candle;
import com.ivo.core.PontosDaAcao;
import com.ivo.data.LeitorCSV;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.time.ZoneId;
import java.util.Date;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.MovingAverage;

public class Grafico {

    public Grafico() {

    }

    public ChartPanel Fgrafico(String dir, String nm, int perid, int DiaSem, int MarcaD) throws FileNotFoundException {

        //86400000 = 1 Dia em MS
        int periDMS = 86400000 * perid;
        if (perid > 24) {
            periDMS = 2147483640;
        }

        LeitorCSV arquivo = new LeitorCSV(dir);
        System.out.println("Lendo o arquivo: " + arquivo.getCaminhoArquivo());

        PontosDaAcao ponto = new PontosDaAcao(nm);
        ponto.addSerieCandles(arquivo.gerarSerieCandles());

        OHLCDataItem[] dadosDia = new OHLCDataItem[ponto.getListaCandle().size()];//Dados Diários
        //Tamanho do Array Semanal:
        int crtzA = 0;
        for (int i = 0, g = 0; i < ponto.getListaCandle().size(); i += 5, g++) {
            crtzA = g;
        }

        OHLCDataItem[] dadosSem = new OHLCDataItem[crtzA];//Dados Semanais

        //Calculo Diário:
        for (int i = 0; i < ponto.getListaCandle().size(); i++) {
            Candle candle = ponto.getListaCandle().get(i);
            dadosDia[i] = new OHLCDataItem(Date.from(candle.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    candle.getOpen(),
                    candle.getHigh(),
                    candle.getLow(),
                    candle.getClose(), 0);

        }
        //Calculo Semanal:
        for (int i = 0, g = 0; i < ponto.getListaCandle().size(); i += 5, g++) {
            if (i + 5 <= ponto.getListaCandle().size()) {
                if (ponto.getListaCandle().get(i).getHigh() == 0) {
                    break;
                }

                double maxHigh = ponto.getListaCandle().get(i).getHigh();
                double minLow = ponto.getListaCandle().get(i).getLow();

                Candle candleSem = ponto.getListaCandle().get(i);

                for (int k = i; k < i + 5; k++) {
                    if (maxHigh < ponto.getListaCandle().get(i).getHigh()) {
                        maxHigh = ponto.getListaCandle().get(i).getHigh();
                    }
                    if (minLow > ponto.getListaCandle().get(i).getLow()) {
                        minLow = ponto.getListaCandle().get(i).getLow();
                    }
                }
                dadosSem[g] = new OHLCDataItem(Date.from(candleSem.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        candleSem.getOpen(),
                        maxHigh,
                        minLow,
                        ponto.getListaCandle().get(i + 5).getClose(), 0);
            }
        }
        //
        DefaultOHLCDataset datasetSem;
        DefaultOHLCDataset datasetDia;
        datasetDia = new DefaultOHLCDataset(dadosDia[0], dadosDia);
        datasetSem = new DefaultOHLCDataset(dadosSem[0], dadosSem);

        //Maior e Menor Valor entre os Candles
        double max = -999999999.99;
        double min = 999999999.99;
        for (int i = 0; i < ponto.getListaCandle().size(); i++) {
            Candle candle = ponto.getListaCandle().get(i);
            if (max < candle.getHigh()) {
                max = candle.getHigh();
            }
            if (min > candle.getLow()) {
                min = candle.getLow();
            }
        }
        max = max + ((max - min) * 0.05);
        min = min - ((max - min) * 0.05);
        ///////////////////////

        JFreeChart grafico = ChartFactory.createCandlestickChart(ponto.getTicker(), "Dias", "Preço", datasetDia, false);
        JFreeChart graficoSem = ChartFactory.createCandlestickChart(ponto.getTicker(), "Semanas", "Preço", datasetSem, false);
        //Plota Diário ou Semanal?:
        if (DiaSem == 1) {
            graficoSem.getPlot().setBackgroundPaint(Color.LIGHT_GRAY);

            XYPlot plot = (XYPlot) graficoSem.getPlot();
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(min, max);
            //Marca D'Agua:
            if (MarcaD == 1) {
                final Marker marcaDagua = new ValueMarker((max + min) / 2);
                marcaDagua.setLabel(nm);
                marcaDagua.setLabelTextAnchor(TextAnchor.CENTER);
                marcaDagua.setLabelAnchor(RectangleAnchor.CENTER);
                marcaDagua.setLabelFont(new Font("Arial", Font.BOLD, 72));
                marcaDagua.setAlpha(0.1f);
                plot.addRangeMarker(marcaDagua);
            }
            //MediaMovel:
            XYDataset dataMMv = MovingAverage.createMovingAverage(datasetSem, "Teste", periDMS, 0);
            plot.setDataset(1, dataMMv);
            plot.setRenderer(1, new StandardXYItemRenderer());
            ///////

            ChartPanel graf = new ChartPanel(graficoSem);
            graf.setMouseWheelEnabled(true);
            graf.setMouseZoomable(true);
            return graf;

        } else {

            grafico.getPlot().setBackgroundPaint(Color.LIGHT_GRAY);

            XYPlot plot = (XYPlot) grafico.getPlot();
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(min, max);
            //Marca D'Agua:
            if (MarcaD == 1) {
                final Marker marcaDagua = new ValueMarker((max + min) / 2);
                marcaDagua.setLabel(nm);
                marcaDagua.setLabelTextAnchor(TextAnchor.CENTER);
                marcaDagua.setLabelAnchor(RectangleAnchor.CENTER);
                marcaDagua.setLabelFont(new Font("Arial", Font.BOLD, 72));
                marcaDagua.setAlpha(0.1f);
                plot.addRangeMarker(marcaDagua);
            }
            //MediaMovel:
            XYDataset dataMMv = MovingAverage.createMovingAverage(datasetDia, "Teste", periDMS, 0);
            plot.setDataset(1, dataMMv);
            plot.setRenderer(1, new StandardXYItemRenderer());
            ///////

            ChartPanel graf = new ChartPanel(grafico);
            graf.setMouseWheelEnabled(true);
            graf.setMouseZoomable(true);
            return graf;

        }
    }
}
