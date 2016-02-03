package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.DoProcess;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;

/**
 * Nr 2
 * Variables: S_t; T_t,_T
 * @author mathc_000
 *
 */
public class CPPIDoProcess extends DoProcess {

	@Override
	public void run() {
		this.doRules.applyDoRules();
	}

	@Override
	public void operate() {
		this.doRules = new CPPIDoRules();
	}
}