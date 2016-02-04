package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

import java.math.BigDecimal;

/**
 * Nr 3
 * Name: Check
 * @author mathc_000
 *
 */
public class CPPICheckProcess extends CheckProcess<BigDecimal> {

    CPPIService service = CPPIService.getInstance();

    public void run() {
    	while(true) {
	    	synchronized (CPPIService.getInstance().doLockObject) {
				try {
					CPPIService.getInstance().doLockObject.wait();
					
					this.objectiveSetting = CPPIObjective.getInstance();
			        
			        this.checkingRules = new CPPICheckRules();
			        this.checkingRules.applyCheckingRules();
			        
			        this.performanceValue = null;
			        
			        synchronized (CPPIService.getInstance().checkLockObject) {
						CPPIService.getInstance().checkLockObject.notify();
					}
			        
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
    	}
    }

    @Override
    public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective, MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
        return null;
    }
}