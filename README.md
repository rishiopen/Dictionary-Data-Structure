Dictionary-Data-Structure
=========================

Implementation of dictionary data structures

Introduction
=========================

Compiler java compiler (javac) tested on Eclipse Compiler for Java (ECJ)

How to compile javac AVLNode.java javac AVLTree.java javac AVLHash.java javac BTree.java javac B_TreeHash?.java javac RBTree.java javac RBHash.java javac dictionary.java

Run using following command

random mode java dictionary -r s BtreeOrder? (Ex. java dictionary -r 3 3)

user input mode java dictionary -u filepath (Ex. dictionary -u input.txt)

Function Prototypes
=========================

AVLNode class : Constructs structure of AVL with insert,search,leftrotate and rightrotate as its major methods.

AVLTree class: Creates N=1000000 nodes by calling insert function of AVLNode and searches them.

AVLHash class: Creates a hash N=1000000 nodes using ‘s’ value by calling insert function of AVLNode and searches them.

RBTree class: Implements a TreeMap interface and puts N=1000000 nodes in RBTree.

RBHash class: Implements a hash of TreeMap interface and puts N=1000000 nodes in RBTree.

BTNode class: Creates a simple structure of BTree.

BTree class: Implements all the functions like insert,search,inorder and levelorder traversal.

B_Tree class: Creates N=1000000 nodes in BTree by calling insert function of BTree and searches them.

B_TreeHash: Creates a hash of N=1000000 nodes in BTree by calling insert function of BTree and searches them.

utility class: Invokes insert,search,inorder,levelorder methods of BTree.

Dictionary class: Invokes all the structures and calculates average insert and search time for each structure of 10 iterations with shuffled values.

Note: All functions perform operation on random input and on user input file.
