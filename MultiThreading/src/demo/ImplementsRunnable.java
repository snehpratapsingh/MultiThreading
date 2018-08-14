package demo;
class RunnerRun implements Runnable{
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				System.out.println("hello "+i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class ImplementsRunnable {

	public static void main(String[] args) {
		Thread t1=new Thread(new RunnerRun());
		t1.start();
		Thread t2=new Thread(new RunnerRun());
		t2.start();
	}

}
