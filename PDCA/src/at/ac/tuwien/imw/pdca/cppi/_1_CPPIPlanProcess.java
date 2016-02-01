package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanProcess;

/**
 * 1. Plan = 1abcdefg
 * 2. Do = 2, 2a
 * 3. Check = 3, 3a
 * 4. Act = 4, 4a, 4b
 */

/**
 * Nr 1
 * Name: Planning of Floor Value
 * Description: Should never be below the CPPI portfolio value
 */
public class _1_CPPIPlanProcess extends PlanProcess {

	@Override
	public void run() {
		// TODO set Floor Value
		plan();
	}

	@Override
	public void plan() {
		// TODO Auto-generated method stub
		this.planningRules = null;
	}
}