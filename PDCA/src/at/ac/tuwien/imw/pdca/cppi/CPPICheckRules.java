package at.ac.tuwien.imw.pdca.cppi;
import java.math.BigDecimal;
import java.math.RoundingMode;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

/**
 * Nr 3a
 */
public class CPPICheckRules implements CheckingRules {

	@Override
	public void applyCheckingRules() {
		CPPIService service = CPPIService.getInstance();
		//CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		//-------------------------------------------------------
		// CALCULATE TSR
		//-------------------------------------------------------
		if(service.getCurrentPeriod() > 0) {
			BigDecimal change = service.getCurrentStockPrice().divide(service.getPreviousStockPrice(), 10, RoundingMode.HALF_UP);
			service.getCppiValues().setTsr(change.subtract(new BigDecimal(1)));
		}
	}
}