package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.DoRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIDoRules implements DoRules {

	@Override
	public void applyDoRules() {
		CPPIService service = CPPIService.getInstance();
		//CPPIPlanConfiguration conf = service.getPlanConfiguration();
		
		// TODO no calculation? :((
	}
}
