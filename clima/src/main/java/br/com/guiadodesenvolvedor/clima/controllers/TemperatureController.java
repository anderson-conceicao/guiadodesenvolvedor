package br.com.guiadodesenvolvedor.clima.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.guiadodesenvolvedor.clima.model.TemperatureData;
import br.com.guiadodesenvolvedor.clima.services.WeatherService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
	@Autowired
	private WeatherService weatherService;

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<TemperatureData> streamTemperature(@RequestParam String city) {
		return Flux.interval(Duration.ofSeconds(5)).flatMap(tick -> {
			Mono<Double> temperatureMono = weatherService.getTemperature(city);
			Mono<TemperatureData> dataMono = temperatureMono.map(temperature -> new TemperatureData(city, temperature));
			return dataMono;
		});
	}
}