package com.rogan.thread;

//´æ¿îÏß³Ì
public class DepositThread extends Thread {
	Account account;
	public DepositThread(Account account){
		this.account = account;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			account.deposit(100);
		}
	}
	
}
