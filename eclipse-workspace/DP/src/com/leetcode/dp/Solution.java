package com.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[][] triangle = {{2}, {3 ,4}, {6, 5, 7}, {4, 1, 8, 3}};

		List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(2));
		List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(3, 4));
		List<Integer> l3 = new ArrayList<Integer>(Arrays.asList(6, 5, 7));
		List<Integer> l4 = new ArrayList<Integer>(Arrays.asList(4, 1, 8, 3));

		List<List<Integer>> t = new ArrayList<>();
		t.add(l1);
		t.add(l2);
		t.add(l3);
		t.add(l4);

		Solution sl = new Solution();
		sl.minimumTotal(t);

		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		sl.maxSubArray(nums);

		int[] numsLIS = { 10, 9, 2, 5, 3, 7, 101, 18 };
		int LIS = sl.lengthOfLIS(numsLIS);

		System.out.println("LIS" + LIS);

		int[] arr = {2, 3, -2, 4};
		int res = sl.maxProduct(arr);
	}

	// 131. Palindrome Partitioning
	// Company: Bloomberg
	// Description: Given a string s, partition s such that every substring of the
	// partition is a palindrome. Return all possible partitions.
	// eg, aab => ["aa", "b"] and ["a", "a", "b"]
	// Solution:
	public List<List<String>> partition(String s) {
		return null;
	}

	private boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {
			if (s.charAt(left++) != s.charAt(right--)) {
				return false;
			}
		}

		return true;
	}

	// 132. Palindrome Partitioning II
	// Company: N/A
	// Description: Given a string s, partition s such that every substring of the
	// partition is a palindrome. Return minimum cut.
	// For eg, "aab", - > 'aa' & 'b' minimum cut is 1.
	// Solution:
	public int minCut(String s) {
		char[] arr = s.toCharArray();
		int n = s.length() - 1;
		int m = s.length() - 1; // maximum cut is length - 1;
		int[] dp = new int[s.length()];
		boolean[][] p = new boolean[s.length()][s.length()]; // starting j to i.

		for (int i = 0; i < arr.length; i++) {
			m = i;
			for (int j = 0; j <= i; j++) {
				if (arr[i] == arr[j] && ((j + 1 > i - 1) || p[j + 1][i - 1])) {
					p[j][i] = true;  //starting j to i is palindrome
					m = j == 0 ? 0: Math.min(m, dp[j - 1] + 1);
				}
				dp[i] = m;
			}
		}

		return dp[n];
	}

	// 300. Longest Increasing Subsequence
	// Company: Microsoft.
	// Description: Given an unsorted array of integers, find the length of longest
	// increasing subsequence.
	// Solution: Two loops, use i for the starter element, and then j start from i +
	// 1, track the latter element and count.
	// [10, 9, 2, 5, 3, 7, 101, 18]
	public int lengthOfLIS2(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}

		int res = 0;
		// f[i]表示以i结尾的最长递增子序列的长度
		int[] dp = new int[nums.length];
		Arrays.fill(dp, 1);

		for (int j = 1; j < nums.length; j++) {
			for (int i = 0; i < j; i++) {
				if (nums[j] > nums[i]) {
					dp[j] = Math.max(dp[j], dp[i] + 1); // ending j, if nums[j] is greater than nums[i].
				}
			}
			res = Math.max(dp[j], res);
		}

		return res;
	}

	// 152. Maximum Product Subarray
	// Company: LinkedIn
	// Description: Find the Maximum product of the subarray.
	// Solution: Use DP, record the minimum product along the way as well.
	public int maxProduct2(int[] nums) {
		int res = nums[0];
		int min_local = nums[0];
		int max_local = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int temp = max_local;
			max_local = Math.max(Math.max(nums[i], temp * nums[i]), min_local * nums[i]);
			min_local = Math.min(Math.min(nums[i], temp * nums[i]), min_local * nums[i]);
			res = Math.max(res, max_local);
		}

		return res;
	}

	// 53. Maximum Subarray
	// Company: Microsoft Bloomberg LinkedIn
	// Description: Given an interger nums, find the contiguous subarray which has
	// the largest sum and return its sum.
	// Solution: dp[i] = max(arr[i], dp[i - 1] + arr[i]);
	public int maxSubArray(int[] nums) {
		// int[] dp = new int[nums.length];
		// dp[0] = nums[0];
		int local_max = nums[0];
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			local_max = Math.max(nums[i], local_max + nums[i]);
			res = Math.max(res, local_max);
		}

		return res;
	}

	// 120. Triangle
	// Company: N/A
	// Description: Only able to move to ADJACENT numbers.
	// Solution:

	public int minimumTotal2(List<List<Integer>> triangle) {
		// row col.
		int[][] sum = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
		sum[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < triangle.size(); i++) {
			List<Integer> cols = triangle.get(i);
			for (int j = 0; j < cols.size(); j++) {
				if (j == 0) {
					sum[i][j] = sum[i - 1][j] + triangle.get(i).get(j);
				} else if (j == cols.size() - 1) {
					sum[i][j] = sum[i - 1][j - 1] + triangle.get(i).get(j);
				} else {
					sum[i][j] = Math.min(sum[i - 1][j - 1] + triangle.get(i).get(j),
							sum[i - 1][j] + triangle.get(i).get(j));
				}
			}
		}

		// iterate through the lower level.
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < sum[sum.length - 1].length; i++) {
			if (minSum > sum[sum.length - 1][i]) {
				minSum = Math.min(minSum, sum[sum.length - 1][i]);
			}
		}

		return minSum;
	}

	// NOTE: This is wrong, due to not satisfy the adjacent condition.
	public int minimumTotal2(List<List<Integer>> triangle) {
		// f[i, j] = min(f[])
		int sum = 0;
		for (int i = 0; i < triangle.size(); i++) {
			sum += findMin(triangle.get(i));
		}

		return sum;
	}

	private int findMin(List<Integer> level) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < level.size(); i++) {
			min = Math.min(level.get(i), min);
		}

		return min;
	}

	// 120. Triangle
	// Description: Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
	// Company: Google
	// Solution: Start from bottom to top, iterate and sum the value up.
	// Time: O(n^2), Space O(1);
	public int minimumTotal(List<List<Integer>> triangle) {
		int row = triangle.size();
		for (int i = row - 2; i >= 0; i--) {
			for (int j = 0; j < i + 1; j++) {
				int old = triangle.get(i).get(j);
				int newValue = old + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
				triangle.get(i).set(j, newValue);
			}
		}

		return triangle.get(0).get(0);
	}

	// 53. Maximum Subarray
	// Description: Find the contiguous subarray which contribute to the most sum
	// Company:
	// Solution: DP, dp[i] = dp[i - 1] + num[i];
	// Time: O(N) space O(N)
	public int maxSubArray(int[] nums) {
		int n = nums.length;
		int[] f = new int[n];
		f[0] = nums[0];
		for (int i = 1; i < n; i++) {
			f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
		}

		int res = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			res = Math.max(f[i]);
		}

		return res;
	}

	// 152. Maximum Product Subarray
	// Description: Find the contiguous max product from subarray.
	// Company: LinkedIn
	// Solution: Similar to 53. Maximum Subarray, however we need to track the minimum value as well.
	// Time: O(N), space: O(1)
	public int maxProduct(int[] nums) {
		int local_max = nums[0];
		// Integer.MIN_VALUE;
		int local_min = nums[0];
		// Integer.MAX_VALUE;
		int res = local_max;

		for (int i = 1; i < nums.length; i++) {
			int temp = local_max;
			local_max = Math.max(Math.max(local_max * nums[i], nums[i]), nums[i] * local_min);
			local_min = Math.min(Math.min(local_min * nums[i], nums[i]), temp * nums[i]);
			res = Math.max(local_max, res);
		}

		return res;
  }

	// 300. Longest Increasing Subsequence
	// Description: Given a array, find the longest increasing subsequence, return the length.
	// Company: LinkedIn
	// Solution: f(i) = Max(f(j) + 1, f(i))
	public int lengthOfLIS(int[] nums) {
		int size = nums.length;

		if (size <= 1) {
        return size;
    }

		int[] f = new int[size];
		Arrays.fill(f, 1);
    int max = 1;

		for (int i = 1; i < size; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					f[i] = Math.max(f[i], f[j] + 1);
				}
			}
        max = Math.max(f[i], max);
		}

		// debug purpose
    for (int i = 0; i < size; i ++ ){
        System.out.println("fn:" + f[i]);
    }

		return max;
	}
}
