package at.ac.tuwien.imw.pdca;



public abstract class PlanProcess<T> implements Runnable {
	
	protected PlanningRules<T> planningRules;
	
	public abstract void plan();

}
