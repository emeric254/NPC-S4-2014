import java.util.Random;


public class Activite implements Runnable{

	@Override
	public void run() {

		System.out.println(Thread.currentThread().toString());
		
		/*try{
			m();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		try {
			f();
		} catch (MonException e) {
			e.printStackTrace();
		}
	}
	
	public void m() {
		n();
	}
	
	public void n() {
		int y=0;	
		y = 1/y;
	}
	
	public void f() throws MonException {
		if(new Random().nextInt(99)<20) throw new MonException();
	}
}
