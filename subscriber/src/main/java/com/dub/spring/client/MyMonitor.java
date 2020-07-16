package com.dub.spring.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.dub.spring.convexHull.ConvexHullPointDist;
import com.dub.spring.convexHull.ConvexHullService;
import com.dub.spring.convexHull.JSONSnapshot;
import com.dub.spring.convexHull.StepResult;
import com.dub.spring.model.JarvisMessage;
import com.dub.spring.model.PointDist;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyMonitor {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private List<StepResult> results = new ArrayList<>();
	
	@Autowired
	private ConvexHullService chService;
	
	@Autowired
	private SimpMessagingTemplate template;// provided
	
	public MyMonitor() {		
		System.out.println("MyMonitor constructed with template == null "
				+ (template == null));
	}
		
	private PointDistWebClient pwc = new PointDistWebClient();
		
	public void run() {
		System.out.println("MyMonitor::run");
		while (true) {
			
			// clear context
			results.clear();
			PointDist pointDist = pwc.getResult();
			
			/** 
			 * prepare Jarvis algorithm 
			 * by creating a new ConvexHullPointDist object
			*/
			ConvexHullPointDist chPointDist 
					= new ConvexHullPointDist(pointDist);
			
			// check consistency
			System.out.println("chPointDist "
					+ chPointDist.getPoints().size());
			
			// init algorithm
			chPointDist.init();
			
			JSONSnapshot snapshot;
			StepResult result; 
			
			// actual algorithm execution
			while (!chPointDist.isFinished()) {
				chPointDist.marchStep();
				// store the step result
				snapshot = chService.pointsToJSON(chPointDist);
				result = new StepResult();
				result.setSnapshot(snapshot);
				if (chPointDist.isFinished()) {
					result.setStatus(StepResult.Status.FINISHED);
				} else if (chPointDist.isNewVertexFound()) { 
					result.setStatus(StepResult.Status.REDRAW);
				} else {
					result.setStatus(StepResult.Status.STEP);
				}
				
				results.add(result);
			}
			
			// prepare INIT message
			JarvisMessage message = new JarvisMessage(); 
			message.setKey(JarvisMessage.Key.INIT);
			message.setPayload(pointDist);
			
			// prepare RESULTS message
			JarvisMessage resMes = new JarvisMessage();
			resMes.setKey(JarvisMessage.Key.RESULTS);
			resMes.setPayload(results);
			
			// display resMes for debug only
			try {
				System.out.println(mapper.writeValueAsString(resMes));
			} catch (Exception e) {
				
			} 
			
			try {						
				template.setMessageConverter(new MappingJackson2MessageConverter());
						
				// first send INIT message
				template.convertAndSend("/topic/jarvis", message);
			
				// then send RESULTS message
				template.convertAndSend("/topic/jarvis", resMes);
				
			
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}// while
	}		
}


