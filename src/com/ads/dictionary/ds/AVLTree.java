package com.ads.dictionary.ds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class AVLTree {
	static long startInsert = 0;
	static long finishInsert = 0;
	static long startSearch = 0;
	static long finishSearch = 0;

	// A function to call Insert of AVLNode class

	static AVLNode insertInto(AVLNode root, int key, int data) {
		return (AVLNode.insert(root, key, data));
	}

	// Main method

	public static void Main(List<Integer> keys, String o, String path)
			throws IOException {
		/* Write on console */

		if (o.equals("-r")) {

			AVLNode root = null;

			startInsert = System.currentTimeMillis(); // Set start Insert time

			// Insert N keys

			for (int key : keys) {
				root = insertInto(root, key, 2 * key);
			}

			finishInsert = System.currentTimeMillis(); // Set finish Insert time

			startSearch = System.currentTimeMillis(); // Set start Search time

			// Search N keys

			for (int key : keys) {
				AVLNode.Search(key, root);
			}

			finishSearch = System.currentTimeMillis(); // Set finish Search time
		}

		/* Write in FILE */

		else if (o.equals("-u")) {

			AVLNode root = null;

			// Create a list of keys and values.

			List<Integer> key = new ArrayList<Integer>();
			List<Integer> value = new ArrayList<Integer>();

			// Read from file

			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;

			// reading line by line from file

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
				root = insertInto(root, key.get(k), value.get(k));
			}

			// Write to output file

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"AVL_inorder.out"));
			out.write(AVLNode.InOrder(root, ""));
			// Writing In order to file.
			out.close();

			// Writing post order to file
			out = new BufferedWriter(new FileWriter("AVL_postorder.out"));

			out.write(AVLNode.postOrder(root, ""));
			out.close(); // Close file
		}
	} // Main method ends here
} // Main Class ends here