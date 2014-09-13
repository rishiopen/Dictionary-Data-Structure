package com.ads.dictionary.ds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class B_TreeHash {
	static long startInsert;
	static long finishInsert;
	static long startSearch;
	static long finishSearch;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void Main(List<Integer> keys, int s, String Order, String o,
			String path, int N) throws IOException {

		int order = Integer.parseInt(Order);

		/* Write on console */

		if (o.equals("-r")) {
			utility util[] = new utility[s];
			for (int i = 0; i < s; i++)
				util[i] = null;
			ArrayList[] key = new ArrayList[s];
			for (int i = 0; i < s; i++) {
				key[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < N; i++) {
				int KEY = keys.get(i);
				int k = KEY % s;
				key[k].add(KEY);
			}

			for (int i = 0; i < s; i++)
				util[i] = new utility(order - 1, key[i], key[i]);

			startInsert = System.currentTimeMillis(); // Set Insert start time
			for (int i = 0; i < s; i++)
				util[i].insert_random();

			finishInsert = System.currentTimeMillis(); // Set Insert Finish time

			startSearch = System.currentTimeMillis(); // Set search insert time
			for (int i = 0; i < s; i++)
				util[i].search_random();

			finishSearch = System.currentTimeMillis(); // Set finish search time

		}

		/* Write in File */

		else if (o.equals("-u")) {
			utility[] util = new utility[3];
			for (int i = 0; i < 3; i++)
				util[i] = null;

			// Create a list of keys and values.

			ArrayList Key[] = new ArrayList[3];
			ArrayList value[] = new ArrayList[3];

			for (int i = 0; i < 3; i++) {
				Key[i] = new ArrayList<Integer>();
				value[i] = new ArrayList<Integer>();
			}
			// Read from file

			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;

			// reading line by line from file

			if ((str = in.readLine()) == null) {
				System.out.println("Empty File");
				return;
			}
			N = Integer.parseInt(str);
			while ((str = in.readLine()) != null) {
				StringTokenizer t = new StringTokenizer(str);
				int k = Integer.parseInt(t.nextToken()); // Add to key list
				int v = Integer.parseInt(t.nextToken()); // Add to value list
				int i = k % 3;
				Key[i].add(k);
				value[i].add(v);
			}

			// Insert N keys
			for (int i = 0; i < 3; i++) {
				util[i] = new utility(order - 1, Key[i], value[i]);
				util[i].insert_random();
			}

			// Write to output file

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"BTreeHash_level.out")); // Writing level order to file
			for (int i = 0; i < 3; i++) {
				out.write(util[i].levelOrder());
				out.newLine();
			}
			out.close();
			out = new BufferedWriter(new FileWriter("BTreeHash_inorder.out"));
			for (int i = 0; i < 3; i++) {
				out.write(util[i].inOrder());
				out.newLine();
			}
			out.close(); // Close file
		}
	}
}