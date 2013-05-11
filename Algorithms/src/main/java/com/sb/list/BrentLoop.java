package com.sb.list;

public class BrentLoop {

	public boolean hasLoopBrentAlgo(CustomLinkedList<?> list) {
		Node<?> tortoise = list.getStartNode();
		Node<?> hare = list.getStartNode().getNext();
		int stepLimit = 2;
		int limit = 0;

		while (hare != null && hare.getNext() != null) {
			if (tortoise == hare) {
				return true;
			}
			if (limit == stepLimit) {
				tortoise = hare;
				hare = hare.getNext();
				stepLimit *= 2;
				limit = 0;
			} else {
				hare = hare.getNext();
				limit++;
			}

			
		}
		// found the terminating [null] node which means no cycle.
		return false;
	}

}
