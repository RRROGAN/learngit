package com.rogan.thread;

//ȡ���߳�
public class WithdrawThread extends Thread{
	Account account;
	public WithdrawThread(Account account){
		this.account = account;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
		account.withdraw(100);
	}
	}	
}
