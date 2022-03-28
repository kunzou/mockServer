package me.kunzou.mockServer.controller;

import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import me.kunzou.mockServer.domain.RateCard;
import me.kunzou.mockServer.service.RateCardService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

	private RateCardService rateCardService;

	public Controller(RateCardService rateCardService) {
		this.rateCardService = rateCardService;
	}

	@GetMapping(value = "/ratecards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RateCard>> getRateCards() {
		return ResponseEntity.ok().body(rateCardService.getRateCards());
	}

	@PostMapping("/ratecard/add")
	public ResponseEntity<RateCard> add(@RequestBody RateCard rateCard) {
		return ResponseEntity.ok().body(rateCardService.add(rateCard));
	}

	@DeleteMapping("/ratecard/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		rateCardService.delete(id);
		return ResponseEntity.ok().build();
	}
}
