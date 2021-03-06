package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		String p = "A man, a plan, a canal: Panama";
		// 012345678901234567890123456789
		boolean pa = sl.isPalindrome(p);
		System.out.println("pa:" + pa);
		String common = "0123";
		System.out.println(common.substring(0, 2));
		String num = "1.0e5";
		boolean isNum = sl.isNumber(num);

		String path = "/.";
		String simpfy = sl.simplifyPath(path);
		System.out.println("Simpfy:" + simpfy);

		String lastWord = "        ";
		sl.lengthOfLastWord(lastWord);
		String source = "mississippi";
		String target = "issip";
		int idx = sl.strStr(source, target);
		// "mississippi"
		// "issip"
		String intValue = "+1";
		int value = sl.myAtoi(intValue);

		String s = "abcde";
		String t = "ab*df";
		boolean match = sl.isMatchWildcard(s, t);
	}

	// 28. Implement strStr()
	// Company: Facebook Amazon Microsoft Google Tencent Alibaba
	// Description: Implement strStr(). Return the index of the first occurrence of
	// needle in haystack, or -1 if needle is not part of haystack.
	// Solution: O(m * n) complexity, iterate the source string, at each i, starting
	// j,
	// starting k, increasing the j and k, until the k is equal to target's length;
	public int strStr(String source, String target) {
		// return 0 if target is empty.
		if (target.length() == 0) {
			return 0;
		}

		int n = source.length() - target.length() + 1;

		for (int i = 0; i < n; i++) {
			int j = i;
			int k = 0;

			while (source.charAt(j) == target.charAt(k)) {
				j++;
				k++;
				if (k == target.length()) {
					return i;
				}
			}
		}

		return -1;
	}

	// 8. String to Integer (atoi)
	// Company: Microsoft Apple Amazon Adobe Facebook LinkedIn Google Goldman Sachs
	// Bloomberg Alibaba Yahoo Airbnb Baidu
	// Description: Implement atoi which converts a string to an integer.
	// Solution: Handle 1. space. 2. sign +/- 3. non number. 4. exceeding boundary.
	// 5. normal case.
	public int myAtoi(String str) {
		int num = 0; // value
		int sign = 1; // +-
		int i = 0; // indicator

		str = str.trim();
		int length = str.length();
		// 1. empty space.
		while (i < length && str.charAt(i) == ' ') {
			i++;
		}

		if (i == length) {
			return num;
		}

		// 2. sign
		if (str.charAt(i) == '-' || str.charAt(i) == '+') {
			sign = (str.charAt(i) == '-' ? -1 : 1);
			i++;
		}

		while (i < length) {
			char c = str.charAt(i);
			if (c > '9' || c < '0') { // 3. not num
				break;
			}

			// 4. overflow case.
			if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}

			num = 10 * num + (c - '0');
			i++;
		}

		return num * sign;
	}

	// 67. Add Binary
	// Company: Facebook Alibaba Google Adobe Intuit
	// Description: Given two binary strings, return their sum (also a binary
	// string). The input strings are both non-empty and contains only characters 1
	// or 0.
	// Solution: while loop until i < 0 && j < 0 && carry == 0, sum a + b + carry
	// up.
	// insert them backward, using string buffer.
	public String addBinary(String a, String b) {
		int carry = 0;
		int i = a.length() - 1;
		int j = b.length() - 1;
		StringBuilder sb = new StringBuilder();

		while (i >= 0 || j >= 0 || carry > 0) {
			int A = (i < 0 ? 0 : a.charAt(i--) - '0');
			int B = (j < 0 ? 0 : b.charAt(j--) - '0');
			int sum = A + B + carry;
			sb.insert(0, Character.forDigit(sum % 2, 10)); // 1 . 0 . 0
			carry = sum / 2;
		}

		return sb.toString();
	}

	// 5. Longest Palindromic Substring
	// Company: Amazon Adobe Microsoft Google Tencent Alibaba Baidu Uber Facebook
	// Twitter Yelp Bloomberg Expedia eBay Yahoo Zenefits Goo 'Pure Storage' Apple
	// Lyft 'Works Application'.
	// Description: Given a string s, find the longest palindromic substring in s.
	// You may assume that the maximum length of s is 1000.
	// Input: eg. "cbbd" Output: "bb"; Input: "babad" Output: "bab" Note: "aba" is
	// also a valid answer.
	// Solution: Use DP, dp[i][j] stands for starting from i to j, the longest
	// palindromic substring. if dp[i][j] is true, which means the char at i and j
	// are equal and dp[i + 1][j - 1]
	// must be true as well.
	public String longestPalindrome(String s) {
		int start = 0;
		int maxLength = 0;
		int n = s.length();
		boolean dp[][] = new boolean[n + 1][n + 1];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);

				if (dp[i][j] && j - i + 1 > maxLength) {
					maxLength = j - i + 1;
					start = i;
				}
			}
		}

		return s.substring(start, start + maxLength);
	}
	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:

	// 125. Valid Palindrome
	// Company: Facebook Apple Amazon Google Microsoft Adobe Snapchat
	// Description: Given a string, determine if it is a palindrome, considering
	// only alphanumeric characters and ignoring cases.
	// eg. Input: "A man, a plan, a canal: Panama" Output: true
	// Solution: Use two pointers from start and tail, compare the character, skip
	// the non-alpha.
	public boolean isPalindrome(String s) {
		if (s.isEmpty()) {
			return true;
		}

		String p = s.toLowerCase();
		int head = 0;
		int tail = p.length() - 1;

		while (head < tail) {
			if (!Character.isLetterOrDigit(p.charAt(head))) {
				head++;
			} else if (!Character.isLetterOrDigit(p.charAt(tail))) {
				tail--;
			} else {
				if (p.charAt(head) != p.charAt(tail)) {
					return false;
				}

				head++;
				tail--;
			}
		}

		return true;
	}

	// 14. Longest Common Prefix
	// Company: Adobe Microsoft Airbnb IXL Amazon Aetion Google Facebook Alibaba
	// Baidu Yelp Apple Expedia Samsung
	// Description: Write a function to find the longest common prefix string
	// amongst an array of strings. If there is no common prefix, return an empty
	// string "".
	// Input: ["flower","flow","flight"] Output: "fl"
	// Solution: Compare each character position by position with first string,
	// until reaches other strings length or not equal
	// to each other.
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		for (int j = 0; j < strs[0].length(); j++) {
			for (int i = 1; i < strs.length; i++) {
				if (j == strs[i].length() || strs[i].charAt(j) != strs[0].charAt(j)) {
					return strs[0].substring(0, j); // j is not included.
				}
			}
		}

		return strs[0];
	}

	// 65. Valid Number
	// Company: Facebook Google LinkedIn Adobe
	// Description: Validate if a given string is numeric. eg, Some examples:
	// "0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true
	// Note: It is intended for the problem statement to be ambiguous. You should
	// gather all requirements up front before implementing one.
	// Solution: 1. check number, '.', 'e', '+/-', excludes all the illegal cases.
	// 2. Use infinite automata.
	public boolean isNumber(String s) {
		s = s.trim();
		boolean numberSeen = false;
		boolean dotSeen = false;
		boolean eSeen = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c <= '9' && c >= '0') {
				numberSeen = true;
			} else if (c == '.') {
				if (dotSeen || eSeen) {
					return false;
				}
				dotSeen = true;
			} else if (c == 'e') {
				if (!numberSeen || eSeen) {
					return false;
				}
				numberSeen = false;
				eSeen = true;
			} else if (c == '+' || c == '-') {
				if (i != 0 && s.charAt(i - 1) != 'e') { // 1.0e+5 legal
					return false;
				}
			} else {
				return false;
			}
		}

		return numberSeen;
	}

	// 12. Integer to Roman
	// Company: Amazon LinkedIn Microsoft Adobe Alibaba Facebook Google Bloomberg
	// Intuit Airbnb
	// Description: I:1 V:5 X:10 L:50 C:100 D:500 M:1000, subtraction: I can be
	// placed before V (5) and X (10) to make 4 and 9. X can be placed before L (50)
	// and C (100) to make 40 and 90. C can be placed before D (500) and M (1000) to
	// make 400 and 900.
	// Solution: Use mapping table for every Roman number, / get the count and % get
	// the next number.
	public String intToRoman(int num) {
		int[] radix = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuffer sb = new StringBuffer();
		for (int i = 0; num != 0; i++) {
			int count = num / radix[i];
			num %= radix[i];
			for (; count > 0; count--) {
				sb.append(symbols[i]);
			}
		}

		return sb.toString();
	}

	// 38. Count and Say
	// Company: Google Facebook Intuit Microsoft Amazon Adobe Apple eBay Airbnb
	// Description: The count-and-say sequence is the sequence of integers beginning
	// as follows:
	// 1, 11, 21, 1211, 111221, ...
	// 1 is read off as "one 1" or 11.
	// 11 is read off as "two 1s" or 21.
	// 21 is read off as "one 2", then "one 1" or 1211.
	// Given an integer n, generate the nth sequence.
	// Solution: Use count to count how many times, and char to form the current
	// string.
	public String countAndSay(int n) {
		if (n <= 0) {
			return "";
		}

		String base = "1";

		while (--n > 0) {
			StringBuffer sb = new StringBuffer();
			int count = 1;

			for (int j = 1; j < base.length(); j++) {

				if (base.charAt(j) == base.charAt(j - 1)) {
					count++;
				} else {
					sb.append(count);
					sb.append(base.charAt(j - 1));
					count = 1;
				}
			}

			sb.append(count);
			sb.append(base.charAt(base.length() - 1));
			base = sb.toString();
		}

		return base;
	}

	// 49. Group Anagrams
	// Company: Amazon Microsoft Google Uber Yelp Goldman Sachs Facebook Apple
	// Alibaba
	// Adobe Yahoo Airbnb Twitter Bloomberg Pinterest
	// Description: Given an array of strings, group anagrams together.
	// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	// Output:
	// [
	// ["ate","eat","tea"],
	// ["nat","tan"],
	// ["bat"]
	// ]
	// Solution: Sort the input string, use adjacent list to store the other
	// anagrams.
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, ArrayList<String>> map = new HashMap<>();

		for (String str : strs) {
			char[] tmp = str.toCharArray();
			Arrays.sort(tmp);

			String key = new String(tmp);

			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(str);
		}

		List<List<String>> result = new ArrayList<>();

		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			List<String> value = entry.getValue();
			result.add(value);
		}

		return result;
	}

	// 242. Valid Anagram
	// Company: Bloomberg Google Microsoft Amazon Apple Goldman Sachs Facebook
	// Snapchat
	// Description: Given two strings s and t , write a function to determine if t
	// is an anagram of s.
	// eg, Input: s = "anagram", t = "nagaram" => true, Input: s = "rat", t = "car"
	// => false;
	// Solution: 1. Use O(1) memory 2. Use sort and compare side by side.
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] alpha = new int[26];

		for (int i = 0; i < s.length(); i++) {
			++alpha[s.charAt(i) - 'a'];
			--alpha[t.charAt(i) - 'a'];
		}

		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] != 0) {
				return false;
			}
		}

		return true;
	}

	public boolean isAnagram2(String s, String t) {
		char[] s_char = s.toCharArray();
		char[] t_char = t.toCharArray();

		Arrays.sort(s_char);
		Arrays.sort(t_char);

		if (s_char.length != t_char.length) {
			return false;
		}

		for (int i = 0; i < s_char.length; i++) {
			if (s_char[i] != t_char[i]) {
				return false;
			}
		}

		return true;
	}

	// 71. Simplify Path
	// Company: Microsoft Amazon Facebook
	// Description: Given an absolute path for a file (Unix-style), simplify it. eg.
	// /home/ => /home
	// "/a/./b/../../c/" => /c, "/../" => "/", "/home//foo/" => "/home/foo"
	// Solution: Use stack, find the next '/', if multiple '/' or '.' we should skip
	// them. ".." we need to pop the stack.
	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < path.length();) {
			i++;
			int j = path.indexOf('/', i);
			if (j < 0) {
				j = path.length();
			}

			String dir = path.substring(i, j);

			if (!dir.isEmpty() && !dir.equals(".")) { // multiple '///' will cause dir equals empty, '.' means current
														// path, which we should skip.
				if (dir.equals("..")) {
					if (!stack.isEmpty()) {
						stack.pop();
					}
				} else {
					stack.push(dir);
				}
			}

			i = j;
		}

		StringBuffer sb = new StringBuffer();
		if (stack.isEmpty()) {
			sb.append("/");
		} else {
			for (String s : stack) {
				sb.append("/").append(s);
			}
		}

		return sb.toString();
	}

	// 58. Length of Last Word
	// Company: Alibaba
	// Description: Given a string s consists of upper/lower-case alphabets and
	// empty space characters ' ', return the length of last word in the string.
	// If the last word does not exist, return 0;
	// eg. "Hello World", return 5;
	// Solution: 1. Continues length++ until reach first non-alpha character. 2. Use
	// split to find the last word, but multiple "space" will return empty.
	public int lengthOfLastWord2(String s) {
		if (s.length() == 0) {
			return 0;
		}

		String[] arr = s.split(" ");
		if (arr.length == 0) { // "multiple spaces"
			return 0;
		}

		return arr[arr.length - 1].trim().length() != 0 ? arr[arr.length - 1].trim().length() : 0;
	}

	public int lengthOfLastWord(String s) {
		int length = 0;
		char[] c_arr = s.trim().toCharArray();
		for (int i = c_arr.length - 1; i >= 0; i--) {
			char c = c_arr[i];
			if (c != ' ') {
				length++;
			} else {
				return length; // start from alpha and find the first non alpha,
			}
		}

		return length;
	}

	// 205. Isomorphic Strings
	// Company: Google LinkedIn Bloomberg Yahoo Yelp
	// Description: Given two strings s and t, determine if they are isomorphic. Two
	// strings are isomorphic if the characters in s can be replaced to get t.
	// Input could have numeric like "13" "42".
	// Solution: Use HashMap mapping s char to t char and vice versa.
	public boolean isIsomorphic(String s, String t) {
		// int[] s_char = new int[256];
		// int[] t_char = new int[256];

		HashMap<Character, Character> s_char = new HashMap<>();
		HashMap<Character, Character> t_char = new HashMap<>();
		// s and t have the same length;
		for (int i = 0; i < s.length(); i++) {
			char sc = s.charAt(i);
			char tc = t.charAt(i);

			if (!s_char.containsKey(sc)) {
				s_char.put(sc, tc);
			} else {
				if (s_char.get(sc) != tc) {
					return false;
				}
			}

			if (!t_char.containsKey(tc)) {
				t_char.put(tc, sc);
			} else {
				if (t_char.get(tc) != sc) {
					return false;
				}
			}
		}

		return true;
	}

	// 290. Word Pattern
	// Company: Dropbox
	// Description: Given a pattern and a string str, find if str follows the same
	// pattern. Here follow means a full match, such that there is a bijection
	// between a letter in pattern and a non-empty word in str.
	// Solution: Use two hashmap to mapping the character to string and vice versa.
	// Same as 205.
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" "); // use space to seperate.
		if (pattern.length() != words.length) {
			return false;
		}

		HashMap<Character, String> p_s = new HashMap<>();
		HashMap<String, Character> s_p = new HashMap<>();
		for (int i = 0; i < pattern.length(); i++) {
			char p = pattern.charAt(i);
			String s = words[i];

			if (!p_s.containsKey(p)) {
				p_s.put(p, s);
			} else {
				if (!p_s.get(p).equals(s)) {
					return false;
				}
			}

			if (!s_p.containsKey(s)) {
				s_p.put(s, p);
			} else {
				if (s_p.get(s) != p) {
					return false;
				}
			}
		}

		return true;
	}

	// 10. Regular Expression Matching
	// Company: Facebook Microsoft Google Bloomberg Apple # Uber Adobe Yelp Two
	// Sigma Amazon Twitter
	// Description: Given an input string (s) and a pattern (p), implement regular
	// expression matching with support for '.' and '*'.
	// '.' Matches any single character. '*' Matches zero or more of the preceding
	// element.
	// Solution: 1. DP solution, 2. Use dfs + visited memo.

	public boolean isMatchRegularExpression(String s, String p) {
		int m = s.length();
		int n = p.length();
		
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		
		for (int i = 0; i <=m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2] ||  // * act as empty
							   (i > 0) && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'); // repeat once. 
				} else {
					dp[i][j] = (i > 0) && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');  
				}
			}
		}
		
		return dp[m][n];
	}

	// 44. Wildcard Matching
	// Company: Microsoft Facebook Adobe # Amazon Bloomberg Google Uber Coursera Two
	// Sigma
	// Description: "* can match any sequence of char and ? can match any char, except empty." Find out whether s matches p.
	// Solution: 1. DFS solution without backtracking. 2. DFS solution with
	// backtracking. 3. DP solution
	public boolean isMatchWildcard(String s, String p) {
		int m = s.length();
		int n = p.length();
		
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		
		for (int j = 1; j < n + 1; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 1]; // * can act as empty.
			}
		}
		
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i - 1][j] || 	// "* act as any character"
							   dp[i][j - 1]; 	// "* act as empty character"
				} else {
					dp[i][j] = false;
				}
			}
		}
		
		return dp[m][n];
	}

	// 516. Longest Palindromic Subsequence
	// Company:
	// Description:
	// Solution:
}
