package com.sb.recursion;

import com.sb.list.CustomLinkedList;
import com.sb.list.Node;

public class RecursionList {

	public void traverse(CustomLinkedList<?> list) {
		if (list == null || list.getStartNode() == null) return;
		Node<?> node = list.getStartNode();
		traverse(node);
	}

	private void traverse(Node<?> node) {
		if (node == null) return;
		System.out.println(node.getItem().toString());
		traverse(node.getNext());
	}

}
