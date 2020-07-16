package com.dub.spring.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.dub.spring.client.MyMonitor;

@Component
public class StompSubscribeListener 
	implements ApplicationListener<SessionSubscribeEvent> {
	 
    private static final Logger log = LoggerFactory.getLogger(StompSubscribeListener.class);
 
	@Autowired
    private SimpMessagingTemplate template;
    
	@Autowired
	private MyMonitor monitor;
   
	@Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        System.out.println("SessionSubscribeEvent caught "
        		+ (template == null));
        monitor.run();
	}
}
