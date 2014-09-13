package com.ads.dictionary.ds;

import java.util.List;
import java.util.TreeMap;

public class RBTree {

	static TreeMap<Integer, Integer> RBnode;
	static long startInsert = 0;
	static long finishInsert = 0;
	static long startSearch = 0;
	static long finishSearch = 0;

	public static void Main(List<Integer> keys) {
		// Create a TreeMap

		RBnode = new TreeMap<Integer, Integer>();

		startInsert = System.currentTimeMillis(); // Set start Insert time

		// INSERT N KEYS

		for (int key : keys) {
			RBnode.put(key, 2 * key);
		}

		finishInsert = System.currentTimeMillis(); // Set finish Insert time

		startSearch = System.currentTimeMillis(); // Set start Search time

		// Search N keys

		for (int key : keys) {
			RBnode.containsKey(key);
		}

		finishSearch = System.currentTimeMillis(); // Set finish Search time
	} // Main method ends here
} // Class ends here