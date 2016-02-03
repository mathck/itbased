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
		// CALCULATE RiskAssetPercent(Xrt) & RisklessAssetPercent(Xft)
		//-------------------------------------------------------
		BigDecimal leftSide = conf.getLaverage().multiply(service.getCppiValues().getCushion());
		BigDecimal rightSide = conf.getRiskAssetPercent().multiply(conf.getPortfolio());
		
		conf.setRiskAssetPercent( leftSide.min(rightSide) );
		conf.setRisklessAssetPercent( conf.getPortfolio().subtract(conf.getRiskAssetPercent()) );

	}
}