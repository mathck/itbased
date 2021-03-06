package at.ac.tuwien.imw.pdca.cppi;
import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.PlanProcess;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

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
public class CPPIPlanProcess extends PlanProcess<BigDecimal> {

	@Override
	public void run() {
		while(true) {
			synchronized (CPPIService.getInstance().actLockObject) {
				try {
					CPPIService.getInstance().actLockObject.wait();
					
					plan();
					planningRules.applyPlanningRules();
					
					synchronized (CPPIService.getInstance().planLockObject) {
						CPPIService.getInstance().planLockObject.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void plan() {
		planningRules = new CPPIPlanRules();
	}
}