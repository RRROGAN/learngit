package com.rogan.observer;
import java.util.*;
public class ConcreteWatched implements watched{

	List<Watcher> list = new ArrayList<Watcher>();
	@Override
	public void addWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		list.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		list.remove(watcher);
	}

	@Override
	public void notifyWatcher(String str) {
		// TODO Auto-generated method stub
		for (Watcher watcher1 : list) {
			watcher1.update(str);
		}
	}

}
