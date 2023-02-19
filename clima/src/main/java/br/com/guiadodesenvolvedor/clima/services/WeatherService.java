package br.com.guiadodesenvolvedor.clima.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class WeatherService {
  public Mono<Double> getTemperature(String city) {
    WebClient client = WebClient.create("https://api.openweathermap.org/data/2.5/weather");
    return client.get()
      .uri(uriBuilder -> uriBuilder
        .queryParam("q", city)
        .queryParam("appid", "API_KEY")//Realizar o cadastro no site para adiguirir uma chave grátis
        .queryParam("units", "metric")
        .build())
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(body -> {
          JSONObject json = new JSONObject(body);
          double temperature = json.getJSONObject("main").getDouble("temp");
          return Mono.just(temperature);
        });
    }
  }