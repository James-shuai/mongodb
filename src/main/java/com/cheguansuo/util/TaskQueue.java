package com.cheguansuo.util;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue implements Runnable {
	public static Queue<Runnable> queue = new LinkedList();
	
	public static void add(Runnable task){
		queue.add(task);
		startTask();
	}

	public static void startTask() {
		new Thread(new TaskQueue()).start();
	}

	public static void stopTask() {
		TaskQueue.queue.remove();
	}
	
	public static void nextTask(){
		
	}

	public void run() {
		synchronized (TaskQueue.queue) {
			while(true){
				Runnable task = TaskQueue.queue.peek();
				if (task == null) {
					
					return;
				}
				task.run();
			}			
		}
	}

}
