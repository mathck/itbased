package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

import java.math.BigDecimal;

/**
 * Nr 1g
 * Name: Configure Basic Variables
 * Description: Setting of all relevant variables which are needed for further calculations
 * Variables: d, m, R_0
 * @author mathc_000
 *
 */
public class _1g_CPPIPlanRules implements PlanningRules {

	private BigDecimal floor = new BigDecimal(100);

	@Override
	public Object applyPlanningRules() {
		CPPIService service=CPPIService.getInstance();
		service.getPlanConfiguration().setFloor(floor);
		return null;
	}
}