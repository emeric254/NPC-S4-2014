public class ThreadTest1 {
	public static volatile boolean running = true ; // reste synchro entre les deux threads grace a volatile
	public static void main(String[] args) {
		
		Thread t1 = new Thread() { // un premier Thread qui lit la variable running
			public void run() {
				int counter = 0;
				while (running) { counter++; }
				System.out.println("Thread 1 fini. counter="+counter);
			}
		};
		
		Thread t2 = new Thread() { // un second Thread qui modifie la variable running
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignored) { }
					System.out.println("Thread 2 fini");
					running = false;
				}
		};

		t1.start();
		t2.start();
	}
}
