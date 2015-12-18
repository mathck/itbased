package at.ac.tuwien.imw.pdca;


public abstract class CheckProcess<T> implements Runnable {
	
	public CheckingRules checkingRules;
	public ObjectiveSetting<T> objectiveSetting;
	public MeasuredPerformanceValue<T> performanceValue;
	
	public abstract Deviation<T> getCheckResult(ObjectiveSetting<T> objective, MeasuredPerformanceValue<T> performanceMeasureValue);

	public void setPerformanceValue(MeasuredPerformanceValue<T> performanceValue) {
		this.performanceValue = performanceValue;
	}
}
