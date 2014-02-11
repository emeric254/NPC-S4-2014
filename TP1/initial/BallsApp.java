import java.awt.Color;
import javax.swing.*;

public class BallsApp {

	public static void nap(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			//
			// Print out the name of the tread that caused this.
			//
			System.err.println("Thread " + Thread.currentThread().getName()
					+ " throwed exception " + e.getMessage());
		}
	}

	public static void main(String[] a) {
		// create world (JPanel)
		final BallWorld world = new BallWorld();

		// create main window and add the world in it
		final JFrame win = new JFrame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				win.getContentPane().add(world);
				win.pack();
				win.setVisible(true);
			}
		});

		// set name of thread, for debugging
		Thread.currentThread().setName("MyMainThread");

		// create 4 balls at random intervals
		nap((int) (5000 * Math.random()));
		new Ball(world, 50, 80, 5, 10,50+100*(int)Math.random(), Color.red);
		nap((int) (5000 * Math.random()));
		new Ball(world, 70, 100, 8, 6,50+100*(int)Math.random(), Color.blue);
		nap((int) (5000 * Math.random()));
		new Ball(world, 150, 100, 9, 7,50+100*(int)Math.random(), Color.green);
		nap((int) (5000 * Math.random()));
		new Ball(world, 200, 130, 3, 8,50+100*(int)Math.random(), Color.black);
	}
}
