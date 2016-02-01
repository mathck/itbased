package at.ac.tuwien.imw.pdca.cppi.service;

import at.ac.tuwien.imw.pdca.cppi._1_CPPIPlanProcess;
import at.ac.tuwien.imw.pdca.cppi._1a_CPPIObjective;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.CPPIPlanConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

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

		int NTHREADS = 10;

		CPPIService service = CPPIService.getInstance();
		
		service.init();
		service.setPlanConfiguration(new CPPIPlanConfiguration());
		service.getCppiValues();
		ExecutorService executor  = Executors.newFixedThreadPool(NTHREADS);

		//xyProcess = new CPPITSRxy();
		//xyProcessThread = new Thread(xyProcess);
		//xyProcessThread.start();
		
		//...
		
		// HOW TO DO Synchronization IN JAVA : TUTORIAL ->
		// http://www.fh-wedel.de/~si/vorlesungen/java/OOPMitJava/Multithreading/Synchronisation.html
		
		new Thread(new CPPIStockPriceGenerator()).start();

		Runnable planProcess = new _1_CPPIPlanProcess();
		
		/**
		 * General Idea:
		 * 
		 * start Plan	(which runs 1, 1a, 1b, 1c, 1d, 1e, 1f, 1g)
		 * start Do		(which runs 2, 2a)
		 * start Check	(which runs 3, 3a)
		 * start Act	(which runs 4, 4a, 4b)
		 */

		executor.shutdown();

	}
}
