package at.ac.tuwien.imw.pdca.cppi.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.CPPIPlanConfiguration;

public class CPPISimulation {
	
	private final static Logger log = LogManager.getLogger(CPPISimulation.class);
	
	// TODO Implement me
	// private static CPPIxyProcess xpProcess;
	// ...
	
	// TODO Implement me
	// private static Thread xyProcessThread;
	// ...
	
	/**
	 * main execution class
	 * @param args
	 */
	public static void main(String[] args) {

		CPPIService service = CPPIService.getInstance();
		
		service.init();
		service.setPlanConfiguration(new CPPIPlanConfiguration());
		service.getCppiValues();
		
		//xyProcess = new CPPITSRxy();
		//xyProcessThread = new Thread(xyProcess);
		//xyProcessThread.start();
		
		//...
		
		new Thread(new CPPIStockPriceGenerator()).start();
	}
}
