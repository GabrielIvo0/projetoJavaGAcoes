package com.ivo.core;

import java.util.ArrayList;
import java.util.List;

public class PontosDaAcao {

    private final String ticker;
    private List<Candle> LCandles;

    public PontosDaAcao() {
        this("");
    }

    public PontosDaAcao(String ticker) {
        this.ticker = ticker;
        this.initSerieCandles();
    }

    public String getTicker() {
        return ticker;
    }

    private void initSerieCandles() {
        LCandles = new ArrayList<>();
    }

    public void addCandle(Candle c) {
        LCandles.add(c);
    }

    public void removeCandle(Candle c) {
        LCandles.remove(c);
    }

    public void addSerieCandles(List<Candle> serie) {
        LCandles.clear();
        LCandles.addAll(serie);
    }

    public List<Candle> getListaCandle() {
        return LCandles;
    }

    @Override
    public String toString() {
        StringBuilder strPAcao = new StringBuilder(ticker);
        for (Candle candle : LCandles) {
            strPAcao.append("\n");
            strPAcao.append(candle.toString());
        }
        return strPAcao.toString();
    }
}
