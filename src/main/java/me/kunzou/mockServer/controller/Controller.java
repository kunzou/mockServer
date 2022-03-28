package me.kunzou.mockServer.controller;

import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.kunzou.mockServer.domain.RateCard;
import me.kunzou.mockServer.service.RateCardService;

@RestController
@RequestMapping("/api")
public class Controller {

	private RateCardService rateCardService;

	public Controller(RateCardService rateCardService) {
		this.rateCardService = rateCardService;
	}

	@GetMapping(value = "/ratecards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RateCard>> getRateCards() {
		return ResponseEntity.ok().body(rateCardService.getRateCards());
	}
}
