package com.sb.recursion;

public class Palindrome {
	
	public boolean isPalindrome(String text){
		if(text.length()<=1) return true;
		return text.charAt(0)==text.charAt(text.length()-1) && isPalindrome(text.substring(1, text.length()-1));
	}
}
