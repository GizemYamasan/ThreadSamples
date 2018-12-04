package org.basic.thread.synch;
import java.util.concurrent.Callable;

public class MultiThreadAccessVolatile {
	
	public static void main(String[] args) {
		Car car = new Car();
		new Thread(new Starter(car)).start();
		new Thread(new Stopper(car)).start();
		
			
		
	
	}

	private static final class Starter implements Runnable {

		private Car car; 
		
		public Starter(Car car) {
			this.car = car;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 1000; i++) {
				try {
					car.startWorking();
					System.out.println("Starter> Car is working:" + car.isWorking());
					Thread.sleep(10L);
				}
				catch (Exception e) { 
					e.printStackTrace();
				}
			}
		}
	}
	
	private static final class Stopper implements Runnable {

		private Car car; 
		
		public Stopper(Car car) {
			this.car = car;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 1000; i++) {
				try {
					car.stopWorking();
					System.out.println("Stopper> Car is working:" + car.isWorking());
					Thread.sleep(10L);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static final class Car {
		private volatile boolean isWorking;
		
		public Car() {
			isWorking = false;
		}
		
		public void startWorking() {
			isWorking = true;
		}
		
		public void stopWorking() {
			isWorking = false;
		}
		
		public boolean isWorking() {
			return isWorking;
		}
	}
}
