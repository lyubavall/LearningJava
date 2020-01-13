package ru.academits.lapteva.temperature_converter.model;

public class ConverterFromCelsius implements Converter {
    @Override
    public double getKelvin(double value) {
        return value + 273.15;
    }

    @Override
    public double getFahrenheit(double value) {
        return (9 * value / 5 + 32);
    }

    @Override
    public double getCelsius(double value) {
        return value;
    }
}
