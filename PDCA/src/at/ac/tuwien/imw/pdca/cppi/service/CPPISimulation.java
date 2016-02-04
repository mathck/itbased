package at.ac.tuwien.imw.pdca.cppi.service;

import at.ac.tuwien.imw.pdca.cppi.CPPIPlanProcess;
import at.ac.tuwien.imw.tables.CPPITableDrawer;
import at.ac.tuwien.imw.pdca.cppi.CPPIActProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPICheckProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPIDoProcess;
import at.ac.tuwien.imw.pdca.cppi.CPPIObjective;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.cppi.CPPIPlanConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * main execution class holding all 4 Threads
 */
public class CPPISimulation {
	private final static Logger log = LogManager.getLogger(CPPISimulation.class);
	
	private static CPPIPlanProcess planProcess = new CPPIPlanProcess();
	private static CPPIDoProcess doProcess = new CPPIDoProcess();
	private static CPPICheckProcess checkProcess = new CPPICheckProcess();
	private static CPPIActProcess actProcess = new CPPIActProcess();
	
	private static Thread planProcessThread;
	private static Thread doProcessThread;
	private static Thread checkProcessThread;
	private static Thread actProcessThread;
	
	/**
	 * General Idea:
	 * 
	 * start Plan
	 * start Do
	 * start Check
	 * start Act
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		CPPITableDrawer.Headlines();
		
		CPPIService service = CPPIService.getInstance();
		service.init();
		service.setPlanConfiguration(new CPPIPlanConfiguration());
		service.getCppiValues();
		
		ExecutorService executor  = Executors.newFixedThreadPool(4);
		
		planProcessThread = new Thread(planProcess);
		doProcessThread = new Thread(doProcess);
		checkProcessThread = new Thread(checkProcess);
		actProcessThread = new Thread(actProcess);
		
		executor.execute(planProcessThread);
		executor.execute(doProcessThread);
		executor.execute(checkProcessThread);
		executor.execute(actProcessThread);
		
		// Startschuss!
		synchronized (CPPIService.getInstance().actLockObject) {
			CPPIService.getInstance().actLockObject.notify();
		}
		
		// Wait for last process to end
		synchronized (CPPIService.getInstance().shutdownLockObject) {
			try {
				CPPIService.getInstance().shutdownLockObject.wait();
				
				executor.shutdown();
				CPPITableDrawer.CloseTable();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
