package com.ads.dictionary.ds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class AVLHash {
	static long startInsert = 0;
	static long finishInsert = 0;
	static long startSearch = 0;
	static long finishSearch = 0;

	// A function to call Insert of AVLNode class

	static AVLNode insertInto(AVLNode root, int key, int data) {
		return (AVLNode.insert(root, key, data));
	}

	// Main method
	public static void Main(List<Integer> keys, int s, String o, String Path)
			throws IOException {
		/* Write on console */

		if (o.equals("-r")) {

			AVLNode root[] = new AVLNode[s];

			for (int i = 0; i < s; i++)
				root[i] = null; // Set all roots NULL

			startInsert = System.currentTimeMillis(); // Set start Insert time

			// Insert N keys

			for (int key : keys) {
				int i = key % s;
				root[i] = insertInto(root[i], key, 2 * key);
			}

			finishInsert = System.currentTimeMillis(); // Set finish Insert time

			startSearch = System.currentTimeMillis(); // Set start Search time

			// Search N keys

			for (int key : keys) {
				int i = key % s;
				AVLNode.Search(key, root[i]);
			}

			finishSearch = System.currentTimeMillis(); // Set finish Search time
		}

		/* Write in File */

		else if (o.equals("-u")) {
			AVLNode root[] = new AVLNode[3];
			for (int i = 0; i < 3; i++)
				root[i] = null; // Set Initially NULL

			// Create a list of keys and values.

			List<Integer> key = new ArrayList<Integer>();
			List<Integer> value = new ArrayList<Integer>();

			// Read from file
			BufferedReader in = new BufferedReader(new FileReader(Path));
			String str;

			// Reading line by line from file

			if ((str = in.readLine()) == null) {
				System.out.println("Empty File");
				return;
			}

			while ((str = in.readLine()) != null) {
				StringTokenizer t = new StringTokenizer(str);
				key.add(Integer.parseInt(t.nextToken())); // Add to key list
				value.add(Integer.parseInt(t.nextToken())); // Add to value list
			}

			// Insert N keys

			for (int k = 0; k < key.size(); k++) {
				int KEY = key.get(k);
				int VALUE = value.get(k);
				int i = KEY % 3;
				root[i] = insertInto(root[i], KEY, VALUE);
			}

			// Write in file

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"AVLHash_inorder.out"));
			for (int i = 0; i < 3; i++) {
				out.write(AVLNode.InOrder(root[i], "")); // Writing In order to
															// file.
				out.newLine();
			}

			out.close(); // Close file
		}
	} // Main method ends here
} // Class ends here
