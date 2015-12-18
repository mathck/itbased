package at.ac.tuwien.imw.pdca;


/**
 * 
 * Generic act method result. 
 * 
 * @author ivanstojkovic
 *
 * @param <T>
 */
public abstract class CorrectiveActOutput<T> {
	
	protected T value;

	public CorrectiveActOutput(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
