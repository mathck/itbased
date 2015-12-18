package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CPPIValues {

	private final static Logger log = LogManager.getLogger(CPPIValues.class);

	private CPPIPlanConfiguration conf;
	private BigDecimal portfolio;
	private BigDecimal tsr;
	private BigDecimal floor;
	private BigDecimal cushion;
	private BigDecimal exposure;
	private BigDecimal reserveasset;
	private BigDecimal partRiskyAsset;
	private BigDecimal partRisklessAsset;
	private BigDecimal previousStockPrice;
	private BigDecimal actualStockPrice;

	public CPPIValues(CPPIPlanConfiguration conf) {
		super();
		this.conf = conf;
		portfolio = conf.getPortfolio();
		
		// TODO Implement me
		// some calculations
		// i.e. floor, cushion, exposure, ...
		
		log.info("Configuration period: "+0+", Floor: "+floor.setScale(4, BigDecimal.ROUND_HALF_UP)+", Cushion: "+cushion.setScale(4, BigDecimal.ROUND_HALF_UP)+", Exposure: "+exposure.setScale(4, BigDecimal.ROUND_HALF_UP)+", Reserveasset: "+reserveasset.setScale(4, BigDecimal.ROUND_HALF_UP)+", PartRisky: "+partRiskyAsset.setScale(4, BigDecimal.ROUND_HALF_UP)+", PartRiskless: "+partRisklessAsset.setScale(4, BigDecimal.ROUND_HALF_UP)+", NewPortfolio: "+portfolio.setScale(4, BigDecimal.ROUND_HALF_UP));
	}

	public CPPIValues(CPPIPlanConfiguration conf, BigDecimal portfolio, BigDecimal tsr, BigDecimal floor, BigDecimal cushion,
			BigDecimal exposure, BigDecimal reserveasset, BigDecimal partRiskyAsset, BigDecimal partRisklessAsset, BigDecimal previousStockPrice,
			BigDecimal actualStockPrice) {
		super();
		this.conf = conf;
		this.portfolio = portfolio;
		this.tsr = tsr;
		this.floor = floor;
		this.cushion = cushion;
		this.exposure = exposure;
		this.reserveasset = reserveasset;
		this.partRiskyAsset = partRiskyAsset;
		this.partRisklessAsset = partRisklessAsset;
		this.previousStockPrice = previousStockPrice;
		this.actualStockPrice = actualStockPrice;
	}

	public CPPIPlanConfiguration getConf() {
		return conf;
	}

	public BigDecimal getPortfolio() {
		return portfolio;
	}

	public BigDecimal getTsr() {
		return tsr;
	}

	public BigDecimal getFloor() {
		return floor;
	}

	public BigDecimal getCushion() {
		return cushion;
	}

	public BigDecimal getExposure() {
		return exposure;
	}

	public BigDecimal getReserveasset() {
		return reserveasset;
	}
	
	public BigDecimal getRiskAssetValue() {
		return portfolio.subtract(reserveasset);
	}

	public BigDecimal getPartRiskyAsset() {
		return partRiskyAsset;
	}

	public BigDecimal getPartRisklessAsset() {
		return partRisklessAsset;
	}

	public BigDecimal getPreviousStockPrice() {
		return previousStockPrice;
	}

	public BigDecimal getActualStockPrice() {
		return actualStockPrice;
	}

}
