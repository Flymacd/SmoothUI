package main.resos.monitor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import main.resos.Resolution;

public class Monitor {
	
	public ArrayList<Resolution> resos = new ArrayList<Resolution>();
	public int id;
	
	private Resolution largestRes;
	
	public Monitor(int id, ArrayList<Resolution> resos) {
		this.id = id;
		this.resos = resos;
		setLargestRes();
	}
	
	private void setLargestRes() {
		Resolution biggest = resos.get(0);
		for (Resolution r : resos) {
			Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
			if (ss.getWidth() == r.getWidth() && ss.getHeight() == r.getHeight()) {
				biggest = r;
			}
		}
		largestRes = biggest;
	}
	
	public Resolution getLargestRes() {
		return largestRes;
	}

	public String toString() {
		return "Monitor ID: " + id + "\n" + resos + "\n";
	}
	
}
