package ru.academits.lapteva.temperature_converter.model;

public interface Converter {
    double getKelvin(double value);

    double getFahrenheit(double value);

    double getCelsius(double value);
}
