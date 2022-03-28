package me.kunzou.mockServer.service;

import java.util.List;
import me.kunzou.mockServer.domain.RateCard;

public interface RateCardService {
	List<RateCard> getRateCards();
	void add(RateCard rateCard);
	void delete(RateCard rateCard);
	RateCard save(RateCard rateCard);
	void clearAllRateCards();
	String exportAsText();
	void importFromText(String data) throws Exception;
}
