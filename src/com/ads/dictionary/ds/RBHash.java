package com.ads.dictionary.ds;

import java.util.List;
import java.util.TreeMap;

public class RBHash {

	static long startInsert = 0;
	static long finishInsert = 0;
	static long startSearch = 0;
	static long finishSearch = 0;

	@SuppressWarnings("unchecked")
	public static void Main(List<Integer> keys, int s) {

		// Create an array TreeMap

		TreeMap<Integer, Integer> Node[] = new TreeMap[s];

		for (int i = 0; i < s; i++)
			Node[i] = new TreeMap<Integer, Integer>();

		startInsert = System.currentTimeMillis(); // Set start Insert time

		// Insert N keys

		for (int key : keys) {
			int i = key % s;
			Node[i].put(key, 2 * key);
		}

		finishInsert = System.currentTimeMillis(); // Set finish Insert time

		startSearch = System.currentTimeMillis(); // Set start Search time

		// Search N keys

		for (int key : keys) {
			int i = key % s;
			Node[i].containsKey(key);
		}

		finishSearch = System.currentTimeMillis(); // Set finish Search time
	}
}