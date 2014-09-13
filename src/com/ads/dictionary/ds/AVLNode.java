package com.ads.dictionary.ds;

class AVLNode {
	int key;
	AVLNode left;
	AVLNode right;
	int height;
	int data;

	/*
	 * Function that allocates a new node with the given key and data with NULL
	 * left and right pointers and default height 1
	 */
	static AVLNode newNode(int key, int data) {
		AVLNode node = new AVLNode();
		node.key = key;
		node.left = null;
		node.right = null;
		node.height = 1;
		node.data = data;
		return node;
	}

	// A function to right rotate subtree rooted with y.

	static AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode z = x.right;

		// Perform rotation
		x.right = y;
		y.left = z;

		// Update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x; // Return new root
	}

	// A utility function to left rotate subtree rooted with x.
	static AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode z = y.left;

		// Perform rotation
		y.left = x;
		x.right = z;

		// Update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		return y; // Return new root
	}

	// Get Balance factor of node.
	static int Balance(AVLNode node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	// A function to get height of the tree
	static int height(AVLNode node) {
		if (node == null)
			return 0;
		return node.height;
	}

	// A function to calculate in order traversal of the tree and stores it in a
	// string variable.

	static String InOrder(AVLNode root, String s) {
		if (root != null) {
			s = InOrder(root.left, s);
			s += root.data + " ";
			s = InOrder(root.right, s);

		}
		return s;
	}

	// A function to calculate Post order traversal of the tree and stores it in
	// a string variable.

	static String postOrder(AVLNode root, String s) {
		if (root != null) {
			s = postOrder(root.left, s);
			s = postOrder(root.right, s);
			s += root.data + " ";
		}
		return s;
	}

	// Insert function

	static AVLNode insert(AVLNode node, int key, int data) {

		if (node == null)
			return (newNode(key, data));

		if (key < node.key)
			node.left = insert(node.left, key, data);
		else
			node.right = insert(node.right, key, data);

		/* Update height of this ancestor node */
		node.height = Math.max(height(node.left), height(node.right)) + 1;

		/* Get the balance factor of this ancestor node */

		int balance = Balance(node);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the unaltered node pointer */
		return node;
	}

	// A function to search a key.

	static void Search(int key, AVLNode node) {

		if (node == null)
			return; // NOT FOUND
		if (node.key == key)
			return; // FOUND
		if (node.key > key)
			Search(key, node.left);
		else
			Search(key, node.right);

	}
} // AVLNode class ends here
