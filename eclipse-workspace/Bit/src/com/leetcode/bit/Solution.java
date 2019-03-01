package com.leetcode.bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		List<String> res = sl.findRepeatedDnaSequences("AAAAAAAAAAAAA");
		System.out.println("Repeat: " + res);
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

	// 190. Reverse Bits
	// Company:
	// Description:
	// Solution:
	// you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res = 0;
		for (int i = 0; i < 32; ++i) {
			if ((n & 1) == 1) {
				res = (res << 1) + 1;
			} else {
				res = res << 1;
			}
			n = n >> 1;
		}

		return res;
  }

	// 187. Repeated DNA Sequences
	// Company: LinkedIn
	// Description:
	// Solution: TODO Memory profiling.
	public List<String> findRepeatedDnaSequences(String s) {
		HashMap<String, Integer> map = new HashMap<>();
		List<String> arr = new ArrayList<String>();
		// HashSet<String> set = new HashSet<>();
		for (int i = 0; i < s.length() - 9; i++)
		{
			String key = s.substring(i, i + 10); // 10 bits in total.
			if (map.containsKey(key))
			{
				if (map.get(key) == 1)
				{
					arr.add(key);
				}
				map.put(key, map.get(key) + 1);
			}
			else
			{
				map.put(key, 1);
			}
		}

		return arr;
  }

	// 191. Number of 1 Bits
	// Company:
	// Description:
	// Solution:
	public int hammingWeight(int n) {
      int count = 0;
			// while (n != 0)
			for (int i = 0; i < 32; i++)
			{
				if ((n & 1) == 1)
				{
					count++;
				}
				n = n >>> 1;
			}

			return count;
  }

	// 89. Gray Code
	// Company: Amazon
	// Description:
	// Solution:
	public List<Integer> grayCode(int n) {
			int loop = (int)Math.pow(2, n);
			List<Integer> res  = new ArrayList<Integer>();
			for (int i = 0; i < loop; i++)
			{
				res.add(i ^ (i >> 1));
			}

			return res;
  }

	// 136. Single Number
	// Company: Airbnb Palantir
	// Description:
	// Solution: 0 XOR b equals to b, a XOR a = 0.
	public int singleNumber(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++)
		{
			res ^= nums[i];
		}

		return res;
	}

	//137. Single Number II
	// Company:
	// Description:
	// Solution:
	public int singleNumber(int[] nums) {

  }

}
