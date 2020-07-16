package com.dub.spring.model;

import java.util.ArrayList;
import java.util.List;

// wrapper class
public class PointDist {

	protected List<Point> points = new ArrayList<>();

	public PointDist() {
		
	}
	
	// deep copy 
	public PointDist(PointDist that) {
		//List<Point> pList = new ArrayList<>();
		for (int i = 0; i < that.points.size(); i++) {
			this.points.add(new Point(that.points.get(i)));
		}
	}
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
	
}
