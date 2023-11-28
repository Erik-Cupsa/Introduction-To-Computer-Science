package finalproject;

public class MyPair<K,V> {
	private K key;
	private V value;
	/*
	 * Constructor
	 */
	public MyPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Returns key of this pair
	 */
	public K getKey() {
		return this.key;
	}
	/**
	 * Return Value of this pair
	 */
	public V getValue() {
		return this.value;
	}

	/**
	 * Set the value of this pair
	 */
	public void setValue(V value) {
		this.value = value;
	}
}

