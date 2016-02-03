package at.ac.tuwien.imw.pdca.cppi;
import at.ac.tuwien.imw.pdca.PlanProcess;

/**
 * @author mathc_000
 * 
 * Plan, Do, Check, Act
 * https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/PDCA_Cycle.svg/2000px-PDCA_Cycle.svg.png
 * 
 * 1. Plan = 1abcdefg (use PlanProcess, PlanningRules, PlanConfiguration)
 * 2. Do = 2, 2a (use DoRules, DoProcess)
 * 3. Check = 3, 3a (use CheckingRules, CheckProcess)
 * 4. Act = 4, 4a, 4b (use ActProcess, CorrectiveActOutput??, CorrectiveActRules??)
 */

/**
 * Nr 1
 * Name: Planning of Floor Value
 * Description: Should never be below the CPPI portfolio value
 *
 * This here is obviously intended for 1g.
 */
public class CPPIPlanProcess extends PlanProcess {

	@Override
	public void run() {
		plan();
	}

	@Override
	public void plan() {
		planningRules = new CPPIPlanRules();
		planningRules.applyPlanningRules();
	}
}