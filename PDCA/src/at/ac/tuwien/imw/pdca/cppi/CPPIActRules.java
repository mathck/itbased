package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.CorrectiveActRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActRules implements CorrectiveActRules {

	@Override
	public void applyActRules() {
		CPPIService service = CPPIService.getInstance();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//-------------------------------------------------------
		// CALCULATE Xrt & Xft
		//-------------------------------------------------------
		BigDecimal leftSide = conf.getLaverage().multiply(service.getCppiValues().getCushion());
		BigDecimal rightSide = conf.getRiskAssetPercent().multiply(conf.getPortfolio());
		
		BigDecimal Xrt = leftSide.min(rightSide);
		BigDecimal Xft = conf.getPortfolio().subtract(Xrt);
		
		//-------------------------------------------------------
		// CALCULATE W
		//-------------------------------------------------------
		//=Xrt*(1+TSR)+Xft*(1+planConf.risklessAssetInterest)^(1/days)
		BigDecimal step1 = Xrt.multiply(service.getDeviationValue().add(new BigDecimal(1)));
		BigDecimal step2 = Xft.multiply(conf.getRisklessAssetInterest().add(new BigDecimal(1)));
		double step3 = (1 / conf.getRisklessAssetLastDays());
		
		BigDecimal W = new BigDecimal(Math.pow(step2.doubleValue(), step3)).add(step1);
		conf.setPortfolio(W);
	}
}