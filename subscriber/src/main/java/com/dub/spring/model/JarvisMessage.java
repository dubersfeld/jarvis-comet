package com.dub.spring.model;

public class JarvisMessage {

	private Key key;
	
	private Object payload;
	
	
	
	public Key getKey() {
		return key;
	}



	public void setKey(Key key) {
		this.key = key;
	}



	public Object getPayload() {
		return payload;
	}



	public void setPayload(Object payload) {
		this.payload = payload;
	}



	public static enum Key {
		INIT, RESULTS
	} 
}
