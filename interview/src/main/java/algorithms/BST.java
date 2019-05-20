package algorithms;

/**
 * 二叉查找树(Binary Search Tree)
 * @param <Key>   键
 * @param <Value> 值
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;              // root of BST

	private class Node {
		private Key key;            // sorted by key
		private Value val;          // associated data
		private Node left, right;   // left and right subtrees
		private int size;           // number of nodes in subtree

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public BST() {
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return size(root);
	}

	/**
	 * @return number of key-value pairs in BST rooted at x
	 */
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 * and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else return x.val;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	/**
	 * Returns the smallest key in the symbol table.
	 * @return
	 */
	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		return min(x.left);
	}


	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) return x;
		return max(x.right);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table
	 * (if the key is in this symbol table).
	 * @param key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			x.right = delete(x.right, key);
		} else if (cmp < 0) {
			x.left = delete(x.left, key);
		} else {
			if (x.left == null) return x.right;
			if (x.right == null) return x.left;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right); // PS：这一步必须放在left赋值前，否则deleteMin()会误删新赋值的left
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void print() {
		print(root);
	}

	private void print(Node x) {
		if (x == null) return;
		print(x.left);
		System.out.println(x.key);
		print(x.right);
	}

	public static void main(String[] args) {
		BST bst = new BST();
		bst.put("D", "d");
		bst.put("X", "d");
		bst.put("Y", "y");
		bst.put("A", "a");
		bst.put("E", "e");
		bst.print();
		System.out.println("==============最小节点==============");
		System.out.println(bst.max());
		System.out.println("==============删除==============");
		bst.delete("X");
		bst.print();
	}
}
