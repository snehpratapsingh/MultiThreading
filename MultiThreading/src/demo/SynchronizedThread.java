package demo;

public class SynchronizedThread {
	private int count=0;
	public static void main(String[] args) {
		SynchronizedThread st= new SynchronizedThread();
		st.doWork();
		
	}
	public void doWork() {
		Thread t1=new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					count++;
				}
			}
		});
		Thread t2=new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					count++;
				}
			}
		});
		t1.start();
		//this creates a new thread to run() returns immediately
		//if we print value of count here, it would be 0
		t2.start();
		
		try {
			t1.join();
			//join will wait for t1 thread to complete
			//after it returns, next lines would execute
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Count="+count);
	}
}
