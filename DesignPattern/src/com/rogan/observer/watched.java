package com.rogan.observer;

public interface watched {
	public void addWatcher(Watcher watcher);
	public void removeWatcher(Watcher watcher);
	public void notifyWatcher(String str);
}
