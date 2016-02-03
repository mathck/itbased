package at.ac.tuwien.imw.pdca.cppi;

import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

/**
 * Nr 1a
 * Name: Floor Value
 * Description : Floor value at the end of the investment period
 * Floor value objective
 * @author mathc_000
 *
 */
public class CPPIObjective extends ObjectiveSetting<BigDecimal> {

	private static CPPIObjective instance = null;

	public CPPIObjective() {
		this.setObjectiveSetting(new BigDecimal(100));
	}
	
	public static CPPIObjective getInstance() {
	   if(instance == null) {
	      instance = new CPPIObjective();
	   }
	   return instance;
	}
}