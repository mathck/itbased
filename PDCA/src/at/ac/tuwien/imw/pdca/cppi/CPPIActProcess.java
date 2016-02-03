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
public class CPPIActProcess extends ActProcess<CorrectiveActRules, BigDecimal> {

	@Override
	public void run() {
		correctiveActRules.applyActRules();
	}

	@Override
	public CorrectiveActOutput<CorrectiveActRules> act(Deviation<BigDecimal> deviation) {
		CPPIService.getInstance().setDeviationValue(deviation.getValue());
		correctiveActRules = new CPPIActRules();
		
		return (CorrectiveActOutput<CorrectiveActRules>) correctiveActRules;
	}
}