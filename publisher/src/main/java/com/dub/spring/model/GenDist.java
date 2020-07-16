package com.dub.spring.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GenDist {
	
	private static final int dmin = 20;
	
	private boolean start = true;
	
	private PointDist fixedDist; 
	
	// clean this stuff
	public PointDist randomize(int Npoints) {
		
		PointDist dist = new PointDist();
		List<Point> points = dist.getPoints();
		int count = 0;
		int ind = 0;
		while (points.size() < Npoints) {
			// generate a random point
			int x = (int)Math.floor(Math.random() * 600) + 50;// range
			int y = (int)Math.floor(Math.random() * 500) + 50;// range
			Point point = new Point(count, x, y);
			
			int i = 0; 
			// check minimal distance to all existing points  
			for (i = 0; i < points.size(); i++) {
				if (distance(point, points.get(i)) < dmin) { 
		          break; 
		        }   
			}// for  
			if (i == points.size()) {
				point.setIndex(ind++);  
				points.add(point);// accept point    
			} else {   
				continue;// reject point     
			}    
			count++;
		}// while
		
		return dist;
	}// randomize
	
	public PointDist fixedDist() {
		if (start) {
			fixedDist = this.randomize(20);
			start = false;
		}
		return fixedDist;
	}
	
	private int distance(Point p1, Point p2) {
    
		double dist = Math.sqrt(Math.pow(p1.getXpos() - p2.getXpos(), 2) + Math.pow(p1.getYpos() - p2.getYpos(), 2));
		return (int)dist;  
	}// distance
}

