package com.cheguansuo.util;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueueDuo implements Runnable {
public Queue<Runnable> queue = new LinkedList();
private Thread thread ;
	
	public void add(Runnable task){
		queue.add(task);
		startTask();
	}

	public void startTask() {
		thread = new Thread(this);
		thread.start();
	}

	public void stopTask() {
		queue.remove();
	}
	
	public void nextTask(){
		
	}

	public void stop(){
		if(thread!=null)
			thread.stop();
	}
	
	public void run() {
		synchronized (queue) {
			while(true){
				Runnable task = queue.peek();
				if (task == null) {
					//thread.stop();
					return;
				}
				task.run();
			}			
		}
	}	

}