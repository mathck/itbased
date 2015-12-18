package at.ac.tuwien.imw.pdca;


/**
 * 
 * Generic deviation value. 
 * In most cases it will be a value (decimal number) that means something to the system.
 * 
 * @author ivanstojkovic
 *
 * @param <T>
 */
public abstract class Deviation<T> {

	private Long id;

	protected T value;

	public Deviation(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public Long getId() {
		return id;
	}

}
