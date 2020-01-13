package ru.academits.lapteva.temperature_converter.model;

public class ConverterFromFahrenheit implements Converter {
    @Override
    public double getKelvin(double value) {
        return (value - 32) * 5 / 9 + 273.15;
    }

    @Override
    public double getFahrenheit(double value) {
        return value;
    }

    @Override
    public double getCelsius(double value) {
        return (value - 32) * 5 / 9;
    }
}
