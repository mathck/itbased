package at.ac.tuwien.imw.pdca.cppi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.cppi.CPPIPlanConfiguration;
import at.ac.tuwien.imw.pdca.cppi.CPPITSR;
import at.ac.tuwien.imw.pdca.cppi.CPPITSRChange;
import at.ac.tuwien.imw.pdca.cppi.CPPIValues;

/**
 * PDCA Service is a singleton that holds the state of a PDCA process
 * 
 * @author ivanstojkovic
 */
public class CPPIService {
	
	private final static Logger log = LogManager.getLogger(CPPIService.class);

	private static CPPIService instance;

	//	control interval in seconds
	public static final int CONTROL_INTERVAL = 1;

	private CPPIPlanConfiguration planConfiguration;

	private BigDecimal currentDeviationValue;

	private int currentPeriod;
	private MeasuredPerformanceValue<BigDecimal> currentTSR;
	//	Stock price that was used for calculation of current portfolio value
	private BigDecimal previousStockPrice;
	//	new stock price that is going to be used for calculation of new portfolio value
	private BigDecimal currentStockPrice;
	//	new stock price that is going to be used for calculation of new portfolio value
	private Deviation<BigDecimal> tsrChange;

	//	predefined stock prices
	private ArrayList<Integer> stockPrices;

	//	Wrapper for all cppi values (exposure, reserve asset, etc.) 
	private CPPIValues cppiValues;

	private CPPIService() {
	}

	public static synchronized CPPIService getInstance() {
		if (instance == null) {
			instance = new CPPIService();
		}
		return instance;
	}
	
	public void init() {
		currentDeviationValue = new BigDecimal(0.0);
		currentPeriod = 0;
		currentStockPrice = new BigDecimal(100);
		previousStockPrice = new BigDecimal(100);
		currentTSR = new CPPITSR(new BigDecimal(100));
		stockPrices = new ArrayList<Integer>(Arrays.asList(new Integer[] {102, 105, 110, 115, 115, 115, 117, 120, 119, 116, 116, 116, 114, 118, 120, 125, 130, 123, 119, 116, 115, 114, 113, 120}));
	}

	public BigDecimal getDeviationValue() {
		return currentDeviationValue;
	}

	public void setDeviationValue(BigDecimal deviationValue) {
		this.currentDeviationValue = deviationValue;
	}

	public int getCurrentPeriod() {
		return currentPeriod;
	}

	public synchronized MeasuredPerformanceValue<BigDecimal> getCurrentTSR() {
		return currentTSR;
	}
	
	public void setCurrentTSR(MeasuredPerformanceValue<BigDecimal> currentTSR) {
		synchronized (currentTSR) {
			this.currentTSR = currentTSR;			
		}
	}

	public CPPIPlanConfiguration getPlanConfiguration() {
		return planConfiguration;
	}

	public void setPlanConfiguration(CPPIPlanConfiguration conf) {
		this.planConfiguration = conf;
	}

	public CPPIValues getCppiValues() {
		if (cppiValues == null) {
			cppiValues = new CPPIValues(planConfiguration);
		}
		return cppiValues;
	}

	public void setCppiValues(CPPIValues cppiValues) {
		this.cppiValues = cppiValues;
	}

	public BigDecimal getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(BigDecimal currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public BigDecimal getPreviousStockPrice() {
		return previousStockPrice;
	}

	public void setPreviousStockPrice(BigDecimal previousStockPrice) {
		this.previousStockPrice = previousStockPrice;
	}

	public void updateActualStockPrice() {
		log.info("updateActualStockPrice");
		currentPeriod++;
		previousStockPrice = new BigDecimal(currentStockPrice.doubleValue());
		currentStockPrice = new BigDecimal(stockPrices.get(currentPeriod % stockPrices.size()));
		log.info("CurrentStockPrice: "+currentStockPrice);
	}

	public synchronized void setTSRChange(Deviation<BigDecimal> cppitsrChange) {
		this.tsrChange = cppitsrChange;
	}

	public Deviation<BigDecimal> getTsrChange() {
		return tsrChange;
	}

	public void setTsrChange(Deviation<BigDecimal> tsrChange) {
		this.tsrChange = tsrChange;
	}

}
