package demo;

import java.util.Scanner;

class Processor extends Thread{
	
	//private boolean running=true;
	//thread may cache the running variable to optimize and in that case this running would never change
	//run() would never stop
	private volatile boolean running=true;
	
	//volatile keyword makes sure that the variable is not cached by thread 
	
	public void run() {
		
		while(running) {
			System.out.println("hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void shutdown() {
		running=false;
	}
}
public class VolatileExample {

	public static void main(String[] args) {
		Processor p1=new Processor();
		p1.start();
		System.out.println("press enter to stop..");
		Scanner sc=new Scanner(System.in);
		sc.nextLine();
		
		p1.shutdown();
	}

}
