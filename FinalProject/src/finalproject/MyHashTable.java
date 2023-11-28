package finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets
	private int capacity = 16;
	// load factor needed to check for rehashing
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets;


	// constructors
	public MyHashTable() {
		this.size = 0;
		this.capacity = 16;
		this.buckets = new ArrayList<>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<MyPair<K,V>>());
		}
	}

	public MyHashTable(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity;
		this.buckets = new ArrayList<>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<MyPair<K,V>>());
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		MyPair<K, V> pair = new MyPair<>(key, value);
		int bucketPos = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketPos);

		if(pair.getValue().equals(null) || pair.getKey().equals(null)){
			throw new IllegalArgumentException("Invalid value");
		}
		else if(bucket.isEmpty() || bucket.equals(null)){
			bucket.add(pair);
			this.buckets.set(bucketPos, bucket);
			this.size++;
			if ((double) this.size / this.capacity > this.MAX_LOAD_FACTOR) {
				this.rehash();
			}
		}

		else{
			for(MyPair<K, V> p: bucket){
				if(p.getKey().equals(key)){
					V old = p.getValue();
					p.setValue(value);
					this.buckets.set(bucketPos, bucket);
					return old;
				}
			}
			bucket.addLast(pair);
			this.buckets.set(bucketPos, bucket);
			this.size++;
			if ((double) this.size / this.capacity > this.MAX_LOAD_FACTOR) {
				this.rehash();
			}
		}
		return null;
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		if(key.equals(null)){
			throw new IllegalArgumentException("Invalid Key");
		}
		int bucketPos = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketPos);
		if(bucket.equals(null) || bucket.isEmpty()){
			return null;
		}
		Iterator<MyPair<K,V>> iter = bucket.iterator();
		while(iter.hasNext()){
			MyPair<K,V> p = iter.next();
			if(p.getKey().equals(key)){
				return p.getValue();
			}
		}
		return null;
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1)
	 */
	public V remove(K key) {
		if(key.equals(null)){
			throw new IllegalArgumentException("Invalid Key");
		}
		int bucketPos = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketPos);
		Iterator<MyPair<K,V>> iter = bucket.iterator();
		while(iter.hasNext()){
			MyPair<K,V> p = iter.next();
			if(p.getKey().equals(key)){
				V old = p.getValue();
				bucket.remove(p);
				this.size --;
				this.buckets.set(bucketPos, bucket);
				return old;
			}
		}
		return null;
	}


	/**
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		ArrayList<LinkedList<MyPair<K,V>>> newBuckets = new ArrayList<>(this.capacity*2);
		for (int i = 0; i < this.capacity*2; i++) {
			newBuckets.add(new LinkedList<MyPair<K,V>>());
		}

		this.capacity *=2;
		for(LinkedList<MyPair<K,V>> bucket: this.buckets){
			for(MyPair<K,V> pair: bucket){
				int bucketPos = hashFunction(pair.getKey());
				newBuckets.get(bucketPos).add(pair);
			}
		}
		this.buckets = newBuckets;
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		ArrayList<K> keySet = new ArrayList<>();
		for(int i=0; i<this.capacity; i++){
			LinkedList<MyPair<K, V>> bucket = this.buckets.get(i);
			for(MyPair<K, V> p: bucket){
				keySet.add(p.getKey());
			}
		}
		return keySet;
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		ArrayList<V> valueSet = new ArrayList<>();
		for(int i=0; i<this.capacity; i++){
			LinkedList<MyPair<K, V>> bucket = this.buckets.get(i);
			for(MyPair<K, V> p: bucket){
				V value = p.getValue();
				if(!valueSet.contains(value)){
					valueSet.add(value);
				}
			}
		}
		return valueSet;
	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		ArrayList<MyPair<K, V>> entries = new ArrayList<>();
		for(int i=0; i<this.capacity; i++){
			LinkedList<MyPair<K, V>> bucket = this.buckets.get(i);
			for(MyPair<K, V> p: bucket){
				entries.add(p);
			}
		}
		return entries;
	}



	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		private int bucketIndex = 0;
		private ArrayList<MyPair<K,V>> elements;

		private MyHashIterator() {
			elements = getEntries();
		}

		@Override
		public boolean hasNext() {
			return bucketIndex < elements.size();
		}

		@Override
		public MyPair<K,V> next() {
			if (!hasNext()) {
				throw new IllegalArgumentException();
			}
			MyPair<K,V> element = elements.get(bucketIndex);
			bucketIndex++;
			return element;
		}

	}

}
