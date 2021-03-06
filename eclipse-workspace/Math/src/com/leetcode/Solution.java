package com.leetcode.isprime;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		sl.countPrimes(1);
	}


	public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }

        return count;
    }

		// 202. Happy Number
		// Company:
		// Description:
		// Solution: If not happy number, then there will be a loop.
		public boolean isHappy(int n) {
			HashSet<Integer> set = new HashSet<>();
			while (n != 1)
			{
				int t = 0;
				while (n != 0)
				{
					t += (n % 10) * (n % 10);
					n /= 10;
				}

				n = t;
				if (set.contains(n)) break;
				else set.add(n);
			}

			return n == 1;
    }

		// 263. Ugly Number
		// Company:
		// Description:
		// Solution: 1. Recursion to check whether number could be divided into 2, 3, 5.
		// 2. Non-Recursion
		public boolean isUgly2(int num) {
			while (num >= 2)
			{
				if (num % 2 == 0)
				{
						num /= 2;
				}
				else if (num % 3 == 0)
				{
					num /= 3;
				}
				else if (num % 5 == 0)
				{
					num /= 5;
				}
				else
				{
					return false;
				}
			}

			return num == 1;
		}

		public boolean isUgly(int num) {
			if (num == 0)
			{
				return false;
			}

			if (num == 1)
			{
				return true;
			}

			return helper(num);
	 	}

		private boolean helper(int n)
		{
			if (n == 2 || n == 3 || n == 5)
			{
				return true;
			}

			if (n % 2 == 0)
			{
				return helper(n / 2);
			}
			else if (n % 3 == 0)
			{
				return helper(n / 3);
			}
			else if (n % 5 == 0)
			{
				return helper(n / 5);
			}

			return false;
		}

		// 264. Ugly Number II
		// Company:
		// Description:
		// Solution:
		public int nthUglyNumber(int n) {

    }

		// 313. Super Ugly Number
		// Company: Google
		// Description:
		// Solution:
		public int nthSuperUglyNumber(int n, int[] primes) {

    }

		// 166. Fraction to Recurring Decimal
		// Company: IXL Google
		// Description:
		// Solution:
		// How to handle the recurring number is the key.
		public String fractionToDecimal(int numerator, int denominator) {

	 	}

		// 172. Factorial Trailing Zeroes
		// Company: Bloomberg
		// Description:
		// Solution: Consider how many 5s could a number contribute, for eg, 1...N, if
		// N = 10, means it could contribute two 5s. Since 5 = 1 * 5, 10 = 2 * 5;
		public int trailingZeroes(int n) {
				int res = 0;
				while (n > 0)
				{
						res += n / 5;
						n /= 5;
				}

				return res;
    }
}
