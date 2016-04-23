package com.rogan.observer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Watcher watcher1 = new ConcreteWatcher();
		Watcher watcher2 = new ConcreteWatcher();
		Watcher watcher3 = new ConcreteWatcher();
		watched girl = new ConcreteWatched();
		girl.addWatcher(watcher1);
		girl.addWatcher(watcher2);
		girl.addWatcher(watcher3);
		girl.notifyWatcher("ÂèÂè£¬ÎÒÒª³ÔĞ¡°×ÍÃÄÌÌÇ");
	}

}
