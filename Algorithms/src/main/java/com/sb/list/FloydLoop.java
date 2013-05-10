package com.sb.list;

public class FloydLoop {

	public boolean hasLoopFloydAlgo(CustomLinkedList<?> list) {
		Node<?> tortoise = list.getStartNode();
		Node<?> hare = list.getStartNode().getNext();
		
		while(hare!=null && hare.getNext()!=null) {
			if(tortoise == hare){
				return true;
			}
			tortoise = tortoise.getNext();
			hare = hare.getNext().getNext();
		}
		// found the terminating [null] node which means no cycle.
		return false;
	}

}
