package com.ivo.data;

import com.ivo.core.Candle;
import com.ivo.core.Periodo;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorCSV {

    private String caminhoArquivo;
    private File arquivo;

    public LeitorCSV(String caminhoArquivo) {
        this.setArquivo(caminhoArquivo);
    }

    public LeitorCSV() {
        //this("");
    }

    public void setArquivo(String caminho) {
        this.caminhoArquivo = caminho;
        arquivo = new File(this.caminhoArquivo);
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public File getArquivo() {
        return arquivo;
    }

    public List<Candle> gerarSerieCandles() throws NumberFormatException {
        List<Candle> serie = new ArrayList<>();
        try {
            Scanner dados = new Scanner(arquivo);
            dados.nextLine();
            while (dados.hasNext()) {
                Candle cd = criarCandle(dados.nextLine());
                if (cd.getHigh() == 0 && cd.getLow() == 0 && cd.getOpen() == 0 && cd.getClose() == 0) {
                    cd = criarCandle(dados.nextLine());
                    serie.add(cd);
                } else {
                    serie.add(cd);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao tentar ler arquivo CSV.");
            System.err.println(">> Arquivo: " + getCaminhoArquivo());
            System.err.println(ex);
        }

        return serie;
    }

    private Candle criarCandle(String linha) throws NumberFormatException {
        String[] dados = linha.split(",");
        LocalDate data = gerarData(dados[0]);
        double abertura;
        double maxima;
        double minima;
        double fechamento;
        double volume;

        try {
            abertura = Double.parseDouble(dados[1]);
            maxima = Double.parseDouble(dados[2]);
            minima = Double.parseDouble(dados[3]);
            fechamento = Double.parseDouble(dados[4]);
            volume = Double.parseDouble(dados[5]);
            return new Candle(abertura, maxima, minima, fechamento, volume, Periodo.PORDIA, data);
        } catch (NumberFormatException ex) {
            System.err.println("O Arquivo CSV comtem linha nulas");
            System.err.println(ex);
        }
        return new Candle();
    }

    private LocalDate gerarData(String campo) {
        String[] strData = campo.split("-");
        LocalDate data = LocalDate.of(
                Integer.parseInt(strData[0]),
                Integer.parseInt(strData[1]),
                Integer.parseInt(strData[2]));

        return data;
    }
}
