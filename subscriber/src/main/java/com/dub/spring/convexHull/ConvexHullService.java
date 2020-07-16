package com.dub.spring.convexHull;

import org.springframework.stereotype.Service;

@Service
public class ConvexHullService {

	
	public JSONSnapshot pointsToJSON(ConvexHullPointDist pointDist) {
	
		JSONSnapshot snapshot = new JSONSnapshot();
		snapshot.setCurrentVertex(
					pointDist.getConvexHull().get(pointDist.getConvexHull().size()-1).getIndex());
		
		snapshot.setCand(pointDist.getInd());
		
		return snapshot;
	}
	
}
