public class ThreadTest2 {
	public static /*volatile*/ int i = 0;
	public static void main(String[] args) {
		// lancer 5 threads qui incrémentent i
		for(int j=0;j<5;j++) {
			Thread t1 = new Thread() {
				public void run() {
					for(int j=0; j<100000; j++)
						addingmethod();
				}
			};
			t1.start();
		}
		try {
			Thread.sleep(5000); // devrait suffire largement
		} catch (InterruptedException ignored) {}
		
		System.out.println("Valeur de i : " +i);
		System.out.println("(devrait etre 500000)");
	}
	
	public synchronized static void addingmethod(){ // fonction synchro
		i++;
	}
}