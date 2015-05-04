package com.vincent.standard.ttt;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {
	//qps
	static RateLimiter limiter = RateLimiter.create(10); // 每秒不超过10个任务被提交  
	
	public static void main(String[] args) throws InterruptedException {  
		testRateLimiter();  
    }

	private static void testRateLimiter() throws InterruptedException {
		for(int i = 100; i < 200; i++){
			new Thread(new Task(i)).start();
		}
		TimeUnit.SECONDS.sleep(1L);
		for(int i = 200; i < 300; i++){
			new Thread(new Task2(i)).start();
		}
	}  
	
	static class Task implements Runnable{
		int num;
		public Task(int num) {
			this.num = num;
		}
		@Override
		public void run() {
			//boolean acquire = limiter.tryAcquire(1, 1000, TimeUnit.MILLISECONDS);
			if(limiter.tryAcquire(1, 3000, TimeUnit.MILLISECONDS)){
				System.out.println(num);
			}else{
				System.out.println("system busy:" + num);
			}
			
		}
	}
	
	static class Task2 implements Runnable{
		int num;
		public Task2(int num) {
			this.num = num;
		}
		@Override
		public void run() {
			//boolean acquire = limiter.tryAcquire(1, 1000, TimeUnit.MILLISECONDS);
			if(limiter.tryAcquire(1, 3000, TimeUnit.MILLISECONDS)){
				System.out.println(num);
			}else{
				System.out.println("system busy:" + num);
			}
			
		}
	}
}
