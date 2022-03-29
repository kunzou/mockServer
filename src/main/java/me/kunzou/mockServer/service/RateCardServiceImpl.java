package me.kunzou.mockServer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import me.kunzou.mockServer.dao.RateCardRepository;
import me.kunzou.mockServer.domain.RateCard;

@Service
public class RateCardServiceImpl implements RateCardService {

	private static List<RateCard> rateCards = new ArrayList<>();
	private RateCardRepository rateCardRepository;

	public RateCardServiceImpl(RateCardRepository rateCardRepository) {
		this.rateCardRepository = rateCardRepository;
	}

	@Override
	public List<RateCard> getRateCards() {
		return rateCardRepository.findAll();
	}

	@Override
	public RateCard add(RateCard rateCard) {
		return rateCardRepository.save(rateCard);
	}

	@Override
	public void delete(RateCard rateCard) {
		rateCardRepository.delete(rateCard);
	}

	@Override
	public void delete(Long id) {
		rateCardRepository.deleteById(id);
	}

	@Override
	public RateCard save(RateCard rateCard) {
		return rateCardRepository.save(rateCard);
	}

	@Override
	public void clearAllRateCards() {
		rateCardRepository.deleteAll();
	}

	@Override
	public String exportAsText() {
		return getRateCards().stream()
				.map(RateCard::toString)
				.collect(Collectors.joining("\n"));
	}

	@Override
	public void importFromText(String data) throws Exception {
		if (data != null) {
			clearAllRateCards();
			for (String dataLine : data.split("\n")) {
				String[] line = dataLine.replaceAll(" ", "").split(",");
				add(new RateCard(line[0], line[1], line[2], line[3], line[4], line[5]));
			}
		}
	}

	@Override
	public void importFromCSVFile(MultipartFile file) throws Exception {
		String text = new String(file.getBytes());
		importFromTextNoError(text);
	}

	private void importFromTextNoError(String data) {
		if(data != null) {
			clearAllRateCards();
			for (String dataLine : data.split("\n")) {
				String[] line = dataLine.replaceAll(" ", "").replaceAll("\"", "").split(",");
				try {
					add(new RateCard(line[0], line[1], line[2], line[3], line[4], line[5]));
				} catch (Exception ignored) {
				}

			}
		}
	}
}
