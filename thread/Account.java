/**
 * 2016年4月27日21:22:11
 * 功能：线程同步问题
 * */
package com.rogan.thread;

public class Account {
	
	private double balance;

	public Account() {
		balance = 0;
		System.out.println("Total Money:" + balance);
	}
	
	//取款临界代码
	public synchronized void withdraw(double money){
		if(balance == 0){
			try {
				this.wait();  //放弃同步对象的互斥锁。拥有互斥锁才能执行
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance = balance - money;
		System.out.println("withdraw 100 success");
		notify();  //唤醒拥有该方法的互斥对象的等待队列中的第一个线程，同时自动获得该互斥对象的互斥锁。
	}
	
	//存款临界代码
	public synchronized void deposit(double money){
		if(balance != 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance = balance + money;
		System.out.println("deposit 100 success");
		notify(); 
	}
	
	
}
