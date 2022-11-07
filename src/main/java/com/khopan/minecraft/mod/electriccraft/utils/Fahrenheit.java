package com.khopan.minecraft.mod.electriccraft.utils;

public class Fahrenheit {
	public double Temperature;

	public Fahrenheit(double Temperature) {
		this.Temperature = Temperature;
	}

	public double getTemperature() {
		return this.Temperature;
	}

	public static Fahrenheit of(double Temperature) {
		return new Fahrenheit(Temperature);
	}

	public static Celsius toCelsius(Fahrenheit Fahrenheit) {
		return new Celsius(((Fahrenheit.Temperature - 32.0d) * 5.0d) / 9.0d);
	}

	public static Celsius toCelsius(Kelvin Kelvin) {
		return new Celsius(Kelvin.Temperature - 273.15d);
	}

	public static Fahrenheit toFahrenheit(Celsius Celsius) {
		return new Fahrenheit((Celsius.Temperature * 1.8d) + 32.0d);
	}

	public static Fahrenheit toFahrenheit(Kelvin Kelvin) {
		return new Fahrenheit(1.8d * (Kelvin.Temperature - 273.15d) + 32.0d);
	}

	public static Kelvin toKelvin(Celsius Celsius) {
		return new Kelvin(Celsius.Temperature - 273.15d);
	}

	public static Kelvin toKelvin(Fahrenheit Fahrenheit) {
		return new Kelvin((Fahrenheit.Temperature - 32.0d) * (5.0d / 9.0d) + 273.15d);
	}
}
