package com.ads.dictionary.ds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// BTree NODE

class BTNode {

	int key[];
	int M;
	BTNode node[];
	BTNode parent;
	int value[];

	BTNode(int order) {
		key = new int[order + 1];
		value = new int[order + 1];
		node = new BTNode[order + 2];
		M = 0;
		parent = null;
		for (int i = 0; i <= order + 1; i++) {
			this.node[i] = null;
		}
	}
}

public class BTree {

	int order;
	BTNode rootNode;

	BTree(int Order) {
		order = Order;
		rootNode = new BTNode(order);
		rootNode.key[0] = -1;

	}

	void sortarray(BTNode root) {
		// Insertion sort

		int last = root.M - 1;
		int tempKey, tempValue;
		for (int i = 0; i < last; i++) {
			if (root.key[last] < root.key[i]) {
				tempKey = root.key[last];
				tempValue = root.value[last];
				for (int j = last; j > i; j--) {
					root.key[j] = root.key[j - 1];
					root.value[j] = root.value[j - 1];
				}
				root.key[i] = tempKey;
				root.value[i] = tempValue;
				break;
			}
		}
	}

	void sortOverflowArray(BTNode root) {
		// Insertion sort

		int last = root.M;
		root.node[last + 1] = root.node[last];
		int tempKey, tempValue;
		for (int i = 0; i < last; i++) {
			if (root.key[last] < root.key[i]) {
				tempKey = root.key[last];
				tempValue = root.value[last];
				for (int j = last; j > i; j--) {
					root.key[j] = root.key[j - 1];
					root.value[j] = root.value[j - 1];
				}
				root.key[i] = tempKey;
				root.value[i] = tempValue;
				break;
			}
		}
	}

	void middleToParent(BTNode parent, BTNode left, BTNode right, int middle,
			int value) {

		// In add middle to parent

		int M = parent.M;
		int i;
		for (i = 0; i < M; i++) {
			if (parent.key[i] > middle)
				break;
		}
		// Outside first
		for (int j = M; j > i; j--) {
			parent.key[j] = parent.key[j - 1];
			parent.node[j + 1] = parent.node[j];
			parent.value[j] = parent.value[j - 1];
		}

		// Done finding index

		parent.key[i] = middle;
		parent.value[i] = value;
		parent.node[i] = left;
		left.parent = parent;
		parent.node[i + 1] = right;
		right.parent = parent;

		// Done setting parent

		if (parent.M < order) {

			// Setting parent M

			parent.M += 1;
		} else {
			split(parent);
		}
	}

	void split(BTNode root) {

		// In split into children

		int middle = root.M / 2;

		BTNode left = new BTNode(order);
		BTNode right = new BTNode(order);
		BTNode temp = new BTNode(order);
		for (int i = 0; i < middle; i++) {
			left.key[i] = root.key[i];
			left.value[i] = root.value[i];
			left.M += 1;
			left.node[i] = root.node[i];
		}
		left.node[middle] = root.node[middle];
		for (int i = 0; i < root.M - middle; i++) {
			right.key[i] = root.key[middle + 1 + i];
			right.value[i] = root.value[middle + 1 + i];
			right.M += 1;
			right.node[i] = root.node[middle + 1 + i];
		}
		right.node[right.M] = root.node[root.M + 1];
		temp.key[0] = root.key[middle];
		temp.value[0] = root.value[middle];
		temp.node[0] = left;
		temp.node[1] = right;
		temp.M = 1;
		for (int i = 0; i <= left.M; i++) {
			if (left.node[i] != null)
				left.node[i].parent = left;
		}
		for (int i = 0; i <= right.M; i++) {
			if (right.node[i] != null)
				right.node[i].parent = right;
		}
		// error - root pointer is not modified correctly
		if (root.parent == root) {
			right.parent = temp;
			left.parent = temp;
			temp.parent = temp;

			rootNode = temp;
			rootNode.parent = temp;
		} else {
			middleToParent(root.parent, left, right, root.key[middle],
					root.value[middle]);
		}
	}

	// Inserts key value

	void insert(BTNode root, int key, int value) {

		// New Node

		if (root.key[0] == -1) {

			root.key[0] = key;
			root.value[0] = value;
			root.parent = rootNode;
			root.M += 1;

		}

		else if (root.key[0] != -1 && root.M < order && root.node[0] == null) {

			// Insert in same node

			root.key[root.M] = key;
			root.value[root.M] = value;
			root.M += 1;
			sortarray(root);
		}

		else if (root.key[0] != -1 && root.M == order && root.node[0] == null) {

			// In split

			root.key[root.M] = key;
			root.value[root.M] = value;
			sortOverflowArray(root);
			split(root);
		}

		else if (root.key[0] != -1) {

			// Insert in child node

			int i;
			for (i = 0; i < root.M; i++) {
				if (key < root.key[i])
					break;
			}
			insert(root.node[i], key, value);
		}
	}

	// Generates in order

	String inOrder(BTNode root, String s) {
		if (root == null) {
			return s;
		}
		for (int i = 0; i < root.M; i++) {
			s = inOrder(root.node[i], s);
			s += root.value[i] + " ";

			// Going right

			if (i == root.M - 1)
				s = inOrder(root.node[i + 1], s);
		}
		return s;
	}

	// Generates level order

	String levelOrder(BTNode root, String s) {

		// LEVEL OREDER TRAVERSAL

		Queue<BTNode> que = new LinkedList<BTNode>();
		BTNode temp;
		que.add(root);
		while (!que.isEmpty()) {
			temp = que.peek();
			que.remove();
			for (int i = 0; i < temp.M; i++) {
				s += temp.value[i] + " ";
			}

			for (int i = 0; i <= temp.M; i++) {
				if (temp.node[i] != null)
					que.add(temp.node[i]);
			}
		}
		return s;
	}

	// Search a key

	void search(BTNode root, int key) {
		int i;
		if (root != null) {
			for (i = 0; i < root.M; i++) {
				if (root.key[i] == key)
					return; // Found
				else if (root.key[i] > key) {
					break;
				}
			}
			search(root.node[i], key);
		}

	}

	// Function to call search

	void searchCall(int key) {
		search(rootNode, key);
	}

	// Function to call insert

	void insertCall(int key, int value) {
		insert(rootNode, key, value);
	}

	// In order

	String inOrderCall() {
		return (inOrder(rootNode, ""));
	}

	// Level order

	public String levelOrder(BTNode root) {
		// TODO Auto-generated method stub
		return (levelOrder(rootNode, ""));
	}
}

// Utility class

class utility {

	BTNode root;
	BTree tree;
	List<Integer> key;
	List<Integer> value;
	int order;

	utility(int Order, List<Integer> Data, List<Integer> values) {

		root = null;
		tree = new BTree(Order);
		order = Order;
		key = Data;
		value = values;
	}

	void insert_random() {

		for (int i = 0; i < key.size(); i++)
			tree.insertCall(key.get(i), value.get(i));
	}

	void search_random() {
		for (int keys : key)
			tree.searchCall(keys);
	}

	String inOrder() {
		return (tree.inOrderCall());
	}

	String levelOrder() {
		return (tree.levelOrder(root));
	}
}

// Driver function
class B_Tree {
	static long startInsert;
	static long finishInsert;
	static long startSearch;
	static long finishSearch;

	public static void Main(List<Integer> keys, String Order, String o,
			String path) throws IOException {

		int order = Integer.parseInt(Order);

		/* Write on console */

		if (o.equals("-r")) {
			// List to insert values twice of key
			// List<Integer> val=new ArrayList<Integer>();
			// for(int key:keys)
			// val.add(2*key);
			utility util = new utility(order - 1, keys, keys);

			startInsert = System.currentTimeMillis(); // Set start Insert time
			util.insert_random();
			finishInsert = System.currentTimeMillis(); // Set finish Insert time

			startSearch = System.currentTimeMillis(); // Set start Search time
			util.search_random();
			finishSearch = System.currentTimeMillis(); // Set finish Search time
		}

		else if (o.equals("-u")) {
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
			utility util = new utility(order - 1, key, value);

			util.insert_random();
			util.search_random();

			// Write to output file

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"BTree_sorted.out"));
			out.write(util.inOrder()); // Writing In order to file.
			out.close();
			// Writing post order to file
			out = new BufferedWriter(new FileWriter("BTree_level.out"));
			out.write(util.levelOrder());
			out.close(); // Close file
		}
	}
}