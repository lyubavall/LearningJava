package ru.academits.lapteva.temperature_converter.model;

public class ConverterFromKelvin implements Converter {
    @Override
    public double getKelvin(double value) {
        return value;
    }

    @Override
    public double getFahrenheit(double value) {
        return (value - 273.15) * 9 / 5 + 32;
    }

    @Override
    public double getCelsius(double value) {
        return value - 273.15;
    }
}
