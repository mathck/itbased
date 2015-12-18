package at.ac.tuwien.imw.pdca;



public abstract class DoProcess implements Runnable {
	
	protected DoRules doRules;
	
	public abstract void operate();

}
