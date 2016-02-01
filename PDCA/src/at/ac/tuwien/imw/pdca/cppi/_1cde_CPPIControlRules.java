package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckingRules;
import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

import java.math.BigDecimal;

/**
 * Nr 1c & 1d,e
 * Name: Calculating Rules m, b, C
 * @author mathc_000
 *
 */
public class _1cde_CPPIControlRules implements CheckingRules {

	private BigDecimal leverage; // readiness to assume a risk
	private BigDecimal maximumRiskyFraction;
	private BigDecimal cushion;
	
	CPPIService service = CPPIService.getInstance();

	@Override
	public void applyCheckingRules() {
		CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		conf.setLaverage(leverage);
		conf.setRiskAssetPercent(maximumRiskyFraction);
		
		CPPIValues values = service.getCppiValues();
		values.setCushion(cushion);
	}
}