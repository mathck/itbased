package at.ac.tuwien.imw.pdca.cppi.service;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author ivanstojkovic
 *
 */
public class CPPIStockPriceGenerator implements Runnable {
	
	private final static Logger log = LogManager.getLogger(CPPIStockPriceGenerator.class.toString());


	public CPPIStockPriceGenerator() {
		super();
	}


	public void run() {
		BasicConfigurator.configure();
		
		log.info("CPPIStockPriceGenerator process started");
		while (true) {
			try {
				Thread.sleep(1); // wtf why is this here?
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			CPPIService.getInstance().updateActualStockPrice();
		}
	}

}
