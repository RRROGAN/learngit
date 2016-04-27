package com.rogan.thread;
/*
 * 2016年4月27日21:36:44
 * **/
public class Application {
	public static void main(String[] args) {
		Account account = new Account();
		WithdrawThread withdraw = new WithdrawThread(account);
		DepositThread deposit = new DepositThread(account);
		withdraw.start();
		deposit.start();
	}
}

/*
 * 总结：
 * 1.wait()和notify()这两个方法必须位于临界代码段中。也就是所，执行该方法的线程必须获得了互斥对象的互斥锁。这是因为
 * 这两个方法实际上也是在操作互斥对象的互斥锁：当一个线程调用wait()方法进入阻塞状态，同时会释放互斥对象的互斥锁；
 * 只有当另一个线程调用互斥对象的notify()方法时，该互斥对象的等待队列中的第一个线程才能进入就绪状态。这也就是这两个方法
 * 是作为互斥对象的方法来实现，而不是作为Thread类的方法实现的原因。
 * 
 * 2.wait()方法和notify()方法必须配对使用。当某个线程由于调用某个互斥对象的wait()方法进入阻塞状态时，只有另一个线程调用
 * 该互斥对象的notify()方法才能唤醒该线程，使其进入就绪状态。
 * 
 * java语言为互斥对象提供了两个方法：一个是wait()，一个是notify()，用于对这两个线程进行同步。需要注意的是：
 * 这两个方法虽然用于线程同步，但却不是作为Thread类的方法来提供。
 * wait()方法的语义是：当一个线程执行了该方法后，则该进程进入阻塞状态。释放对象锁，进入等待队列
 * notify()方法的语义是：当一个线程执行了该方法后，将唤醒等待队列中的第一个线程
 * 
 * wait()阻塞自己，notify()唤醒别人
 * */
