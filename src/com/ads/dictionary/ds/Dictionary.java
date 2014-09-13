package com.ads.dictionary.ds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {

	public static void main(String args[]) throws IOException {

		// Check validity of argument

		if (!(args[0].equals("-r") || args[0].equals("-u")))
			throw new IllegalArgumentException();

		int N = 1000000; // N keys to insert.

		// Create a list of keys.

		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++)
			keys.add(i);

		/* Write on console */

		String option = new String(args[0]);

		if (option.equals("-r")) {

			int s = Integer.parseInt(args[1]); // Get S value
			String Order = args[2];

			long totalInsertTime[] = new long[6]; // Total Insert time for each
													// data structure
			long totalSearchTime[] = new long[6]; // Total Search time for each
													// data structure

			int count = 1; // counter

			while (count <= 10) {
				Collections.shuffle(keys); // Shuffle List

				// Call to AVL

				AVLTree.Main(keys, option, "NO PATH");
				totalInsertTime[0] += (AVLTree.finishInsert - AVLTree.startInsert);
				totalSearchTime[0] += (AVLTree.finishSearch - AVLTree.startSearch);

				// Call to AVLHash

				AVLHash.Main(keys, s, option, " NO PATH");
				totalInsertTime[1] += (AVLHash.finishInsert - AVLHash.startInsert);
				totalSearchTime[1] += (AVLHash.finishSearch - AVLHash.startSearch);

				// Call to RedBlack

				RBTree.Main(keys);
				totalInsertTime[2] += (RBTree.finishInsert - RBTree.startInsert);
				totalSearchTime[2] += (RBTree.finishSearch - RBTree.startSearch);

				// Call to RedBlackHash

				RBHash.Main(keys, s);
				totalInsertTime[3] += (RBHash.finishInsert - RBHash.startInsert);
				totalSearchTime[3] += (RBHash.finishSearch - RBHash.startSearch);

				// Call to BTree

				B_Tree.Main(keys, Order, option, "NO PATH");
				totalInsertTime[4] += (B_Tree.finishInsert - B_Tree.startInsert);
				totalSearchTime[4] += (B_Tree.finishSearch - B_Tree.startSearch);

				// Call to BTreeHash

				B_TreeHash.Main(keys, s, Order, option, "NO PATH", N);
				totalInsertTime[5] += (B_TreeHash.finishInsert - B_TreeHash.startInsert);
				totalSearchTime[5] += (B_TreeHash.finishSearch - B_TreeHash.startSearch);

				count += 1; // Increment counter

			}

			// Print Respective Average times.

			System.out.println("Average AVL Insert time : "
					+ (totalInsertTime[0]) / 10);
			System.out.println("Average AVL Search time : "
					+ (totalSearchTime[0]) / 10);

			System.out.println("Average AVLHash Insert time : "
					+ (totalInsertTime[1]) / 10);
			System.out.println("Average AVLHash Search time : "
					+ (totalSearchTime[1]) / 10);

			System.out.println("Average RBTree Insert time : "
					+ (totalInsertTime[2]) / 10);
			System.out.println("Average RBTree Search time : "
					+ (totalSearchTime[2]) / 10);

			System.out.println("Average RBHash Insert time : "
					+ (totalInsertTime[3]) / 10);
			System.out.println("Average RBHash Search time : "
					+ (totalSearchTime[3]) / 10);

			System.out.println("Average BTree Insert time : "
					+ (totalInsertTime[4]) / 10);
			System.out.println("Average BTree Search time : "
					+ (totalSearchTime[4]) / 10);

			System.out.println("Average BTreeHash Insert time : "
					+ (totalInsertTime[5]) / 10);
			System.out.println("Average BTreeHash Search time : "
					+ (totalSearchTime[5]) / 10);
		}

		/* Write in FILE */

		else if (option.equals("-u")) {

			String PATH = args[1]; // Get PATH of Input File

			// Call AVL
			AVLTree.Main(keys, option, PATH);

			// Call AVLHash
			AVLHash.Main(keys, 3, option, PATH);

			// Call BTree
			B_Tree.Main(keys, "3", option, PATH);

			// Call BTreeHash
			B_TreeHash.Main(keys, 3, "3", option, PATH, N);
		}
	}
}