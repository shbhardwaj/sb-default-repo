package com.sb.recursion;
public class TowerOfHanoi {

	public void move(int noOfDisks, String fromTower, String toTower, String usingTower) {
		if (noOfDisks <= 0)	return; // this is the base case
		move(noOfDisks - 1, fromTower, usingTower, toTower);
		System.out.println("Move disk " + noOfDisks + " from  " + fromTower	+ " to " + toTower);
		move(noOfDisks - 1, usingTower, toTower, fromTower);

	}

	public static void main(String[] args) {
		new TowerOfHanoi().move(4, "A", "C", "B");
	}

}
