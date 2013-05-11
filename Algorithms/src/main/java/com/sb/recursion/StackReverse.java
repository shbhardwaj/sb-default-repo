package com.sb.recursion;

import java.util.Stack;

public class StackReverse<E> {
	
	/**
	 * Imagine a stack with elements 1,2,3,4
	 * Consider the 2,3,4 as ONE element, so the stack will look like 1,[2,3,4]
	 * Now swap the the two elements, after swap it will look like [2,3,4], 1
	 * Now repeat the above operations on 2,3,4.
	 * [2,3,4],1
	 * 2,[3,4],1
	 * [3,4],2,1
	 * 3,[4],2,1
	 * [4],3,2,1
	 */

	public void reverse(Stack<E> stack){
		reverse(stack,stack.size()-1);
	}
	
	private void reverse(Stack<E> stack, int top) {
		if(top == -1) return;
		swap(stack, top);
		reverse(stack, top-1);
	}
	
	private void swap(Stack<E> stack, int top) {
		E firtEle = stack.get(0);
		stack.remove(0);
		stack.add(top, firtEle);
				
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(new Integer(1));
		stack.add(new Integer(2));
		stack.add(new Integer(3));
		stack.add(new Integer(4));
		new StackReverse<Integer>().reverse(stack);
		for(Integer data : stack){
			System.out.println(data);
		}
		
		
	}
}
