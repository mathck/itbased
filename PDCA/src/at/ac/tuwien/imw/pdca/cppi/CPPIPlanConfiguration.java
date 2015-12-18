package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.PlanConfiguration;

public class CPPIPlanConfiguration extends PlanConfiguration {
	
	private final static Logger log = LogManager.getLogger(CPPIPlanConfiguration.class);

	private BigDecimal riskAssetPercent = new BigDecimal(0.3);
	private BigDecimal risklessAssetPercent = new BigDecimal(0.7);
	private Integer risklessAssetLastDays = 365;
	private BigDecimal risklessAssetInterest = new BigDecimal(0.05);
	private BigDecimal laverage = new BigDecimal(2.0);
	private BigDecimal portfolio = new BigDecimal(100);

	public CPPIPlanConfiguration() {
		super();
	}

	public CPPIPlanConfiguration(BigDecimal riskAssetPercent, BigDecimal risklessAssetPercent, Integer risklessAssetLastDays,
			BigDecimal risklessAssetInterest, BigDecimal laverage, BigDecimal portfolio) {
		super();
		this.riskAssetPercent = riskAssetPercent;
		this.risklessAssetPercent = risklessAssetPercent;
		this.risklessAssetLastDays = risklessAssetLastDays;
		this.risklessAssetInterest = risklessAssetInterest;
		this.laverage = laverage;
		this.portfolio = portfolio;
	}

	public BigDecimal getPortfolio() {
		if(portfolio != null) {
			portfolio = portfolio.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
		return portfolio;
	}

	public void setPortfolio(BigDecimal portfolio) {
		this.portfolio = portfolio;
	}

	public BigDecimal getRiskAssetPercent() {
		if(riskAssetPercent != null) {
			riskAssetPercent = riskAssetPercent.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
		return riskAssetPercent;
	}

	public void setRiskAssetPercent(BigDecimal riskAssetPercent) {
		this.riskAssetPercent = riskAssetPercent;
	}

	public BigDecimal getRisklessAssetPercent() {
		if(risklessAssetPercent != null) {
			risklessAssetPercent = risklessAssetPercent.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
		return risklessAssetPercent;
	}

	public void setRisklessAssetPercent(BigDecimal risklessAssetPercent) {
		this.risklessAssetPercent = risklessAssetPercent;
	}

	public Integer getRisklessAssetLastDays() {
		return risklessAssetLastDays;
	}

	public void setRisklessAssetLastDays(Integer risklessAssetLastDays) {
		this.risklessAssetLastDays = risklessAssetLastDays;
	}

	public BigDecimal getRisklessAssetInterest() {
		if(risklessAssetInterest != null) {
			risklessAssetInterest = risklessAssetInterest.setScale(4, BigDecimal.ROUND_HALF_UP);
		}
		return risklessAssetInterest;
	}

	public void setRisklessAssetInterest(BigDecimal risklessAssetInterest) {
		if(risklessAssetInterest != null) {
			risklessAssetInterest = risklessAssetInterest.setScale(4, BigDecimal.ROUND_HALF_UP);
		}
		this.risklessAssetInterest = risklessAssetInterest;
	}

	public BigDecimal getLaverage() {
		if(laverage != null) {
			laverage = laverage.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return laverage;
	}

	public void setLaverage(BigDecimal laverage) {
		log.info("setting laverage conf:" +laverage);
		this.laverage = laverage;
	}

	@Override
	public String toString() {
		return "RiskMGTPlanConfiguration [riskAssetPercent=" + riskAssetPercent + ", risklessAssetPercent=" + risklessAssetPercent
				+ ", risklessAssetLastDays=" + risklessAssetLastDays + ", risklessAssetInterest=" + risklessAssetInterest + ", laverage="
				+ laverage + ", portfolio=" + portfolio + "]";
	}

}
