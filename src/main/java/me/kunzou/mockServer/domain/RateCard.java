package me.kunzou.mockServer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class RateCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal buyRate;
	private BigDecimal maxFee;
	private BigDecimal overageSplit;
	private BigDecimal nbAdjustment;
	private BigDecimal regAdjustment;

	public RateCard() {
	}

	public RateCard(String name, String buyRate, String maxFee, String overageSplit, String nbAdjustment, String regAdjustment) {
		this.name = name;
		this.buyRate = new BigDecimal(buyRate);
		this.maxFee = new BigDecimal(maxFee);
		this.overageSplit = new BigDecimal(overageSplit);
		this.nbAdjustment = new BigDecimal(nbAdjustment);
		this.regAdjustment = new BigDecimal(regAdjustment);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(BigDecimal buyRate) {
		this.buyRate = buyRate;
	}

	public BigDecimal getMaxFee() {
		return maxFee;
	}

	public void setMaxFee(BigDecimal maxFee) {
		this.maxFee = maxFee;
	}

	public BigDecimal getOverageSplit() {
		return overageSplit;
	}

	public void setOverageSplit(BigDecimal overageSplit) {
		this.overageSplit = overageSplit;
	}

	public BigDecimal getNbAdjustment() {
		return nbAdjustment;
	}

	public void setNbAdjustment(BigDecimal nbAdjustment) {
		this.nbAdjustment = nbAdjustment;
	}

	public BigDecimal getRegAdjustment() {
		return regAdjustment;
	}

	public void setRegAdjustment(BigDecimal regAdjustment) {
		this.regAdjustment = regAdjustment;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s",
				name,
				buyRate,
				maxFee,
				overageSplit,
				nbAdjustment,
				regAdjustment);
	}

}
