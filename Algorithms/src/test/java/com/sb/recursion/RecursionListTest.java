package com.sb.recursion;

import org.junit.Test;

import com.sb.list.CustomLinkedList;
import com.sb.list.Node;

public class RecursionListTest {
	
	@Test
	public void traverse(){
		Node<String> node1 = new Node<String>("a", null);
		Node<String> node2 = new Node<String>("b", null);
		Node<String> node3 = new Node<String>("c", null);
		Node<String> node4 = new Node<String>("d", null);
		Node<String> node5 = new Node<String>("e", null);
		Node<String> node6 = new Node<String>("f", null);
		CustomLinkedList<String> list = new CustomLinkedList.Builder<String>()
				.withNode(node1).withNode(node2).withNode(node3)
				.withNode(node4).withNode(node5).withNode(node6).build();
		
		new RecursionList().traverse(list);
	}

}
