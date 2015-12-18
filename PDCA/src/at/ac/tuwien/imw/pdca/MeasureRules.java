package at.ac.tuwien.imw.pdca;

/**
 * 
 * Here we specify the system performance measure rules.
 * 
 * @author ivanstojkovic
 *
 */
public interface MeasureRules<T> {
	
	//	specify how the system is going to measure performance
	public MeasuredPerformanceValue<T> measure();
	
}
