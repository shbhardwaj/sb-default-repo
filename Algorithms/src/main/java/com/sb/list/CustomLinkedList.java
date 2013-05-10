package com.sb.list;

public class CustomLinkedList<E> {

	private int size;
	private Node<E> startNode;

	private CustomLinkedList(Builder<E> builder) {
		this.startNode = builder.startNode;
		this.size = builder.size;
	}

	public void add(Node<E> node) {
		if (node == null)
			throw new IllegalArgumentException("Null Node cannot be inserted.");
		node.setNext(null);
		if (startNode == null) {
			startNode = node;
			return;
		} else {
			Node<E> tempNode = startNode;
			while (tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
			}
			tempNode.setNext(node);
		}
		size++;
	}
	

	public int getSize() {
		return size;
	}

	public Node<E> getStartNode() {
		return startNode;
	}

	public static class Builder<E> {
		int size;
		Node<E> startNode;
		Node<E> currentNode = startNode;

		public CustomLinkedList<E> build() {
			return new CustomLinkedList<E>(this);
		}

		public Builder<E> withNode(Node<E> node) {
			if (startNode == null) {
				startNode = node;
			} else {
				currentNode.setNext(node);
			}
			currentNode = node;
			size++;
			return this;
		}
	}

}
