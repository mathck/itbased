package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.DoProcess;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

/**
 * Nr 2
 * Variables: S_t; T_t,_T
 * @author mathc_000
 *
 */
public class CPPIDoProcess extends DoProcess {

	@Override
	public void run() {
		while(true) {
			synchronized (CPPIService.getInstance().planLockObject) {
				try {
					CPPIService.getInstance().planLockObject.wait();
					
					operate();
					this.doRules.applyDoRules();
					
					synchronized (CPPIService.getInstance().doLockObject) {
						CPPIService.getInstance().doLockObject.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void operate() {
		this.doRules = new CPPIDoRules();
	}
}