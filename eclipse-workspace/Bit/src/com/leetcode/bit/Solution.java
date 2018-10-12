package com.leetcode.bit;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 231. Power of Two
	// Company: Amazon
	// Description: Given an integer, write a function to determine if it is a power of two.
	// Solution: 1. % 2 until 0 or 1; 2. Highest bit should be 1, minus 1 other bits will be 1, bit& them to check whether it become 0.
	public boolean isPowerOfTwo(int n) {
		if (n == 2 || n == 1) {
				 return true;
		 }

		if (n % 2 == 0) {
		 	n = n / 2;
		 	return isPowerOfTwo(n);
	 	} else {
		 return false;
	 	}
	}

	public boolean isPowerOfTwo2(int n) {
		return n > 0 && ((n&(n - 1)) == 0);
	}
