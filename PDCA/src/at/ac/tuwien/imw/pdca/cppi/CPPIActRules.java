package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.CorrectiveActRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIActRules implements CorrectiveActRules {

	@Override
	public void applyActRules() {
		CPPIService service = CPPIService.getInstance();
		CPPIValues values = service.getCppiValues();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//-------------------------------------------------------
		// CALCULATE RiskAssetPercent(Xrt) & RisklessAssetPercent(Xft)
		//-------------------------------------------------------
		// =Xrt*(1+TSR) + Xft*(1+0.05)^(1/days)
		
		if(service.getCurrentPeriod() > 0) {
			BigDecimal riskPart = values.getPartRiskyAsset()
					.multiply(BigDecimal.ONE.add(values.getTsr()));
			
			double expo = 1.0d / conf.getRisklessAssetLastDays();
			
			double risklessPartMultiplicator = Math.pow(BigDecimal.ONE.add(conf.getRisklessAssetInterest()).doubleValue(), expo);
			BigDecimal risklessPart = values.getPartRisklessAsset().multiply(new BigDecimal(risklessPartMultiplicator));
			
			BigDecimal Wt = riskPart.add(risklessPart);
			values.setPortfolio(Wt);
		}
		
		//-------------------------------------------------------
		// CALCULATE CUSHION
		//-------------------------------------------------------
		BigDecimal floor = service.getCppiValues().getFloor();
		BigDecimal W = service.getCppiValues().getPortfolio();
		
		BigDecimal cushion = W.subtract(floor).max(new BigDecimal(0));
		service.getCppiValues().setCushion(cushion);
		
		//-------------------------------------------------------
		// CALCULATE RiskAssetPercent(Xrt) & RisklessAssetPercent(Xft)
		//-------------------------------------------------------
		BigDecimal leftSide = conf.getLaverage().multiply(service.getCppiValues().getCushion());
		BigDecimal rightSide = conf.getRiskAssetPercent().multiply(service.getCppiValues().getPortfolio());
		
		service.getCppiValues().setPartRiskyAsset(leftSide.min(rightSide));
		service.getCppiValues().setPartRisklessAsset(service.getCppiValues().getPortfolio().subtract(leftSide.min(rightSide)));
	}
}