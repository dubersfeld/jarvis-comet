package com.dub.spring.model;

// wrapper class
public class PointWrapper {

	Point point;
	
	public PointWrapper(Point point) {
		this.point = point;
	}
	
	public PointWrapper() {
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
