package at.ac.tuwien.imw.pdca.cppi;


import java.math.BigDecimal;

import at.ac.tuwien.imw.pdca.ActProcess;
import at.ac.tuwien.imw.pdca.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.CorrectiveActRules;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

/**
 * Nr 4
 * @author mathc_000
 */
public class CPPIActProcess extends ActProcess<BigDecimal, BigDecimal> { // todo kA ob "CorrectiveActRules, BigDecimal" stimmt

	@Override
	public void run() {
		
		synchronized (CPPIService.getInstance().checkLockObject) {
			while(true) {
				try {
					CPPIService.getInstance().checkLockObject.wait();
					
					act(new CPPIDeviation(new BigDecimal(1)));
					correctiveActRules.applyActRules();
					
					CPPIService.getInstance().updateActualStockPrice();
			        
					// if periode > days
					if(CPPIService.getInstance().getCurrentPeriod() <= CPPIService.getInstance().getPlanConfiguration().getRisklessAssetLastDays()) {
						synchronized (CPPIService.getInstance().actLockObject) {
							CPPIService.getInstance().actLockObject.notify();
						}
					}
					else {
						synchronized (CPPIService.getInstance().shutdownLockObject) {
							CPPIService.getInstance().shutdownLockObject.notify();
						}
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public CorrectiveActOutput<BigDecimal> act(Deviation<BigDecimal> deviation) {
		CPPIService.getInstance().setDeviationValue(deviation.getValue());
		correctiveActRules = new CPPIActRules();
		
		return null; // todo kA ob das stimmt
	}
	
	/**
	 * Round double value, Math is missing this functionality
	 * @param value value to round
	 * @param places how many digits after the dot?
	 * @return rounded double
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}