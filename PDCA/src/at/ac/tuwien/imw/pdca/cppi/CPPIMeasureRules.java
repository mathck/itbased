package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.MeasureRules;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIMeasureRules<T> implements MeasureRules<T> {
	
	@Override
	public MeasuredPerformanceValue<T> measure() {
		CPPIService service = CPPIService.getInstance();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//-------------------------------------------------------
		// CALCULATE W
		//-------------------------------------------------------
		//=Xrt*(1+TSR)+Xft*(1+planConf.risklessAssetInterest)^(1/days)
		BigDecimal step1 = conf.getRiskAssetPercent().multiply(service.getDeviationValue().add(new BigDecimal(1)));
		BigDecimal step2 = conf.getRisklessAssetPercent().multiply(conf.getRisklessAssetInterest().add(new BigDecimal(1)));
		double step3 = (1 / conf.getRisklessAssetLastDays());
		
		BigDecimal W = new BigDecimal(Math.pow(step2.doubleValue(), step3)).add(step1);
		conf.setPortfolio(W);
		
		
		return null;
	}

}
