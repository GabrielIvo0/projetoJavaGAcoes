package com.ivo.core;

import java.time.LocalDate;

public class Candle {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final Periodo tempo;
    private final LocalDate data;

    public Candle() {
        this(0.0, 0.0, 0.0, 0.0, 0.0, Periodo.PORDIA, LocalDate.now());
    }

    public Candle(double abertura, double maxima, double minima, double fechamento, double volume, Periodo tempo, LocalDate data) {
        this.open = abertura;
        this.high = maxima;
        this.low = minima;
        this.close = fechamento;
        this.tempo = tempo;
        this.data = data;
    }

    public double getOpen() {
        return open;
    }

    public LocalDate getData() {
        return data;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public Periodo getTempo() {
        return tempo;
    }

    @Override
    public String toString() {

        return data.toString() + "," + open + "," + high + "," + low + "," + close;
    }
}
