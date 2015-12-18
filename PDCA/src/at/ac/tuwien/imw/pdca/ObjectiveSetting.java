package at.ac.tuwien.imw.pdca;


/**
 * 
 * This is our objective value
 * 
 * @author ivanstojkovic
 *
 */
public abstract class ObjectiveSetting<T> {
	
	private T objectiveSetting;

	public T getObjectiveSetting() {
		return objectiveSetting;
	}

	public void setObjectiveSetting(T objectiveSetting) {
		this.objectiveSetting = objectiveSetting;
	}
}
