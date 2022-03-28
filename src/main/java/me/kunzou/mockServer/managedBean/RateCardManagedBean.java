package me.kunzou.mockServer.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Component;
import me.kunzou.mockServer.domain.RateCard;
import me.kunzou.mockServer.service.RateCardService;

@Component
@ManagedBean
@SessionScoped
public class RateCardManagedBean {

	private String data;
	private List<RateCard> rateCards;
	private RateCardService rateCardService;

	public RateCardManagedBean(RateCardService rateCardService) {
		this.rateCardService = rateCardService;
	}

	@PostConstruct
	public void init() {
		refresh();
	}

	private void refresh() {
		rateCards = rateCardService.getRateCards();
	}

	public void onAddNew() {
		rateCardService.add(new RateCard());
		refresh();
	}

	public void onRowEdit(RowEditEvent<RateCard> event) {
		rateCardService.save(event.getObject());
	}

	public void importFromText() {
		rateCardService.clearAllRateCards();
		try {
			rateCardService.importFromText(data);
			addMessage("Data imported successfully");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,"Error while importing from text", e.getMessage());
		}
		refresh();
	}

	private void addMessage(String summary) {
		addMessage(summary, null);
	}

	private void addMessage(Severity severity, String summary, String message) {
		FacesMessage msg = new FacesMessage(severity,summary, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void addMessage(String summary, String message) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, message);
	}

	public void exportToText() {
		data = rateCardService.exportAsText();
	}

	public void delete(RateCard rateCard) {
		rateCardService.delete(rateCard);
		refresh();
	}

	public List<RateCard> getRateCards() {
		return rateCards;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
