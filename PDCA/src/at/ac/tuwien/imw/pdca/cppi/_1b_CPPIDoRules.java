package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanningRules;

/**
 * Nr 1b
 * Name: Monitoring tasks
 * @author mathc_000
 */
public class _1b_CPPIDoRules implements PlanningRules<Object> {

	@Override
	public Object applyPlanningRules() {
		return null;
		// TODO apply monitoring
		
		// other students solution
		// log.info("Neue Portfoliozusammensetzung: " + CPPIService.getInstance().getCppiValues().getPartRiskyAsset().toPlainString() + " risikoreich veranlagt, " + CPPIService.getInstance().getCppiValues().getPartRisklessAsset() + " risikolos veranlagt.");
	}
}