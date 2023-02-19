package br.com.guiadodesenvolvedor.clima.model;

public class TemperatureData {
	  private final String city;
	  private final double temperature;

	  public TemperatureData(String city, double temperature) {
	    this.city = city;
	    this.temperature = temperature;
	  }

	  public String getCity() {
	    return city;
	  }

	  public double getTemperature() {
	    return temperature;
	  }
	}