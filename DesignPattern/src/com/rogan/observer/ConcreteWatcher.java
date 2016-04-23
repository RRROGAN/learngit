package com.rogan.observer;

public class ConcreteWatcher implements Watcher {
	
	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		System.out.println(str+" "+this.toString());
	}

}
