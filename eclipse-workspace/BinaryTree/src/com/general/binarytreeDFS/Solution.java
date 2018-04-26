package com.general.binarytreeDFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.general.binarytree.TreeNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 543. Diameter of Binary Tree
	// Company: Facebook Google.
	// Description: Longest path between two nodes, may or may not pass the root.
	// Solution: Longest path of tree = longest left path + longest right path;
	private int diameter = 0;
	public int diameterOfBinaryTree(TreeNode root) {
		depthHelper(root);
		return diameter;
	}
	
	private int depthHelper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = depthHelper(node.left);
		int right = depthHelper(node.right);
		diameter = Math.max(diameter, left + right);
		return Math.max(left, right) + 1;
	}

	// 572. Subtree of Another Tree
	// Company: Facebook.
	// Description: Whether t is a descendent of s,
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) {
			return false;
		}

		if (isSameTree2(s, t)) {
			return true;
		}

		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	// same tree or not simpler version of samehelper
	private boolean isSameTree2(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}

		if (r1 == null || r2 == null) {
			return false;
		}

		return r1.val == r2.val && isSameTree2(r1.left, r2.left) && isSameTree2(r1.right, r2.right);
	}

	// 100. Same Tree
	// Company: Bloomberg
	// Description: Given two binary trees, write a function to check if they are
	// the same or not.
	public boolean isSameTree(TreeNode p, TreeNode q) {
		// if (p == null && q == null) {
		// return true;
		// }

		return samehelper(p, q);
	}

	private boolean samehelper(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		// tricky part means either is nil other not.
		if (root1 == null || root2 == null) {
			return false;
		}

		if (root1.val != root2.val) {
			return false;
		}

		return samehelper(root1.left, root2.left) && samehelper(root1.right, root2.right);
	}

	// 101. Symmetric Tree
	// Company: Microsoft, LinkedIn
	// Solution: Recursion to find out whether left right is equal or not.
	// 1
	// / \
	// 2 2
	// \ \
	// 3 3
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return symHelper(root.left, root.right);
	}

	private boolean symHelper(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		// left null, right non-null or left non-null, right null.
		if (left == null || right == null) {
			return false;
		}

		// if (left.val != right.val) {
		// System.out.println("left:" + left.val + "right:" + right.val);
		// return true;
		// }

		if (left.val != right.val) {
			return false;
		}

		return symHelper(left.left, right.right) && symHelper(left.right, right.left);
	}

	// 450. Delete Node in a BST
	// Company: Uber
	// Description: Find a node under BST, delete it and form a new tree.
	// Solution 1. Use Recursion to find the first node to be deleted.
	// if key == root && both left right not null, we need to find the first right
	// minimum element.
	// otherwise, just return the non-null child right or left.
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return root;
		}

		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else {
			// if key == root
			if (root.right != null && root.left != null) {
				TreeNode min = root.right;
				while (min.left != null) {
					min = min.left;
				}
				root.val = min.val;
				root.right = deleteNode(root.right, min.val);
			} else {
				TreeNode newRoot = root.left == null ? root.right : root.left;
				root.left = null;
				root.right = null;
				return newRoot;
			}
		}

		return root;
	}

	// 85. Insert Node in a Binary Search Tree
	// Company: N/A
	// Description: insert a node into existing BST
	// Solution 1. Recursion, 2. Iteration.

	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}

		if (root.val < node.val) {
			root.right = insertNode(root.right, node);
		}

		if (root.val > node.val) {
			root.left = insertNode(root.left, node);
		}

		return root;
	}

	// 11. Search Range in Binary Search Tree LintCode
	// Company: N/A
	// Description given a k1 and k2 find the values in between them on BST.
	// Solution: Recursion check k1 < val || k1 < val < k2 || k2 > val

	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
		// write your code here
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		rangeHelper(res, root, k1, k2);
		return res;
	}

	private void rangeHelper(List<Integer> res, TreeNode node, int k1, int k2) {
		if (node == null) {
			return;
		}

		if (k1 < node.val) {
			rangeHelper(res, node.left, k1, k2);
		}

		if (k1 <= node.val && k2 >= node.val) {
			res.add(node.val);
		}

		if (k2 > node.val) {
			rangeHelper(res, node.right, k1, k2);
		}
	}

	// 366. Find Leaves of Binary Tree
	// Company: Linkedin
	// Description: Collect all the leaves and remove the leaves, repeat.
	// Solution: Backtracking using the postorder traversal and level to add value;
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		leafHelper(res, root);

		return res;
	}

	private int leafHelper(List<List<Integer>> list, TreeNode node) {
		if (node == null) {
			return -1;
		}

		int left = leafHelper(list, node.left);
		int right = leafHelper(list, node.right);
		int level = Math.max(left, right) + 1;

		if (list.size() == level) {
			list.add(new ArrayList<>());
		}

		list.get(level).add(node.val);
		node.left = null;
		node.right = null;
		return level;
	}

	// 230. Kth Smallest Element in a BST
	// Company: Google Uber
	// Description: Find the kth smallest number in BST.
	// Solution: use in-order tranversal.
	private int kth = 0;
	private int kthVal = 0;

	public int kthSmallest(TreeNode root, int k) {
		kth = k;
		inorderHelper(root);
		return kthVal;
	}

	private void inorderHelper(TreeNode root) {
		if (root == null) {
			return;
		}

		inorderHelper(root.left);
		kth--;

		if (kth == 0) {
			kthVal = root.val;
		}

		inorderHelper(root.right);
	}

	// 226. Invert Binary Tree
	// Company: Google
	// Solution 1. Non recursion (BFS) per level, swap the nodes.
	// 2. Recursion, swap left and right node.
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);

		root.left = right;
		root.right = left;

		return root;
	}

	// 597. Maximum average of subtree. LintCode
	// Description: find the subtree who has the maximum average.
	// Solution: Use custom type to record size, sum, divide and conquer
	// calculate the maximum.
	class ResultType {
		TreeNode node;
		int sum;
		int size;

		public ResultType(TreeNode node, int sum, int size) {
			this.node = node;
			this.sum = sum;
			this.size = size;
		}
	}

	private ResultType result;

	public TreeNode findSubtree2(TreeNode root) {
		if (root == null) {
			return null;
		}

		ResultType type = maxSubHelper(root);
		return type.node;
	}

	private ResultType maxSubHelper(TreeNode node) {
		if (node == null) {
			return new ResultType(null, 0, 0);
		}

		ResultType leftResult = maxSubHelper(node.left);
		ResultType rightResult = maxSubHelper(node.right);

		ResultType curResult = new ResultType(node, leftResult.sum + rightResult.sum + node.val,
				leftResult.size + rightResult.size + 1);

		if (result == null || curResult.sum / curResult.size > result.sum / result.size) {
			result = curResult;
		}

		return curResult;
	}

	// 108. Convert Sorted Array to Binary Search Tree
	// Company: airbnb
	// Description:
	// Solution use binary search recursion, question how is time BigO and space
	// BigO?
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}

		return BSTHelper(nums, 0, nums.length - 1);
	}

	private TreeNode BSTHelper(int[] nums, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = left + (right - left) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = BSTHelper(nums, left, mid - 1);
		node.right = BSTHelper(nums, mid + 1, right);

		return node;
	}

	// 250. Count Univalue Subtrees
	// Company: N/A
	// Description: count how much subnodes has the same value.
	// find the subtree and do the count
	int count;

	public int countUnivalSubtrees(TreeNode root) {
		count = 0;
		uniHelper(root);
		return count;
	}

	private boolean uniHelper(TreeNode node) {
		if (node == null) {
			return true;
		}
		boolean left = uniHelper(node.left);
		boolean right = uniHelper(node.right);

		if (left && right) {
			if (node.left != null && node.left.val != node.val) {
				return false;
			}

			if (node.right != null && node.right.val != node.val) {
				return false;
			}
			count++;
			return true;
		}

		return false;
	}

	// 236. Lowest Common Ancestor of a Binary Tree
	// Company: Facebook, Amazon, LinkedIn, Apple
	// Description: Given a binary tree, find the lowest common ancestor (LCA) of
	// two given nodes in the tree.
	// Solution Recursion, find the leaf first,
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		return left == null ? right : left;
	}

	// 235. Lowest Common Ancestor of a Binary Search Tree
	// Company: Facebook Amazon Twitter Microsoft
	// Description: Given a BST find the common parent node. Common node can be
	// itself.
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		// lowest ancestor on the left.
		if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor2(root.left, p, q);
		} else if (root.val < p.val && root.val < q.val) { // lowest ancestor on the right.
			return lowestCommonAncestor2(root.right, p, q);
		} else {
			return root;
		}
		// else if (root.val > p.val && root.val < q.val || root.val < p.val && root.val
		// > q.val) {
		// return root;
		// } else {
		// return null;
		// }
	}

	// 112. Path Sum
	// Company: Microsoft
	// Description: Find whether there are path from root to leaf which can sum up
	// to given value.
	// Solution 1. Recursion 2. Iteration using Stack
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		// reach the leaf of node.
		if (root.left == null && root.right == null) {
			return sum == root.val;
		}

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public boolean hasPathSum2(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			// reach the leaf.
			if (cur.left == null && cur.right == null) {
				if (cur.val == sum) {
					return true;
				}
			}

			if (cur.right != null) {
				stack.push(cur.right);
				cur.right.val += cur.val;
			}

			if (cur.left != null) {
				stack.push(cur.left);
				cur.left.val += cur.val;
			}
		}

		return false;
	}

	// 113. Path Sum II
	// Company: Bloomberg
	// Description: find all root-to-leaf paths where each path's sum equals the
	// given sum.
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		pathSumHelper(root, sum, res, new ArrayList<Integer>());

		return res;
	}

	private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				res.add(new ArrayList<Integer>(list));
			}
		}

		if (root.left != null) {
			pathSumHelper(root.left, sum - root.val, res, list);
		}

		if (root.right != null) {
			pathSumHelper(root.right, sum - root.val, res, list);
		}

		list.remove(res.size() - 1);
	}

	// 257. Binary Tree Paths.
	// Company: Google Facebook Apple
	// Description: Return all the paths which contains
	// root-to-leaf paths
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<String>();

		if (root == null) {
			return res;
		}

		pathHelper(root, String.valueOf(root.val), res);

		return res;
	}

	private void pathHelper(TreeNode root, String path, List<String> res) {
		if (root == null) {
			return;
		}

		// make sure it's the end of leaf.
		if (root.right == null && root.left == null) {
			res.add(path);
			return;
		}

		if (root.left != null) {
			pathHelper(root.left, path + "->" + root.left.val, res);
		}

		if (root.right != null) {
			pathHelper(root.right, path + "->" + root.right.val, res);
		}

	}

	private TreeNode subtree = null;
	private int subTreeSum = Integer.MAX_VALUE;

	// LintCode warm up.
	// Problem: 596 minimum subtree.
	// Description: Find out the minimum value for the subtrees.
	public TreeNode findSubtree(TreeNode root) {
		helper(root);
		return subtree;
	}

	private int helper(TreeNode root) {
		if (root == null)
			return 0;
		int sum = helper(root.left) + helper(root.right) + root.val;

		if (sum < subTreeSum) {
			subTreeSum = sum;
			subtree = root;
		}

		return sum;
	}
}
