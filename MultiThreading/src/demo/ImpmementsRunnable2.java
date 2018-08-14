package demo;

public class ImpmementsRunnable2 {

	public static void main(String[] args) {
		Thread t1= new Thread(new Runnable() {
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
		});
		t1.start();
	}

}
