package com.general.binarytree;

import java.util.Stack;

public class BSTIterator {
	private Stack<TreeNode> stack;
	private TreeNode cur;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 173. Binary Tree Iterator
	// Company: Facebook LinkedIn
	// Description: Use in-order for BST tree, which will output the sorted numbers.
	// Solution: Inorder traversal to find the next smallest value.
	
	public BSTIterator(TreeNode root) {
		cur = root;
		stack = new Stack<>();
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		if (!stack.isEmpty() || cur != null) return true;
		return false;
	}

	/** @return the next smallest number */
	public int next() {
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
		cur = stack.pop();
		int val = cur.val;
		cur = cur.right;
		return val;
	}

}
