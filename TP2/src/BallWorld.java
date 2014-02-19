import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class BallWorld extends JPanel implements MouseListener {

	// default window size
	private final int xSize = 250;
	private final int ySize = 250;
	private Killer killBalls; 

	private final static Color BGCOLOR = Color.white;

	// a BallWorld contains Ball instances 
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	public BallWorld() {
		setPreferredSize(new Dimension(xSize, ySize));
		setOpaque(true);
		setBackground(BGCOLOR);
		this.addMouseListener(this);
		killBalls = new Killer(this);
	}

	// This method is run from the GUI thread when the window needs redrawing
	// It reads the balls array.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Ball b : balls)
			b.draw(g);
	}

	public void addBall(final Ball b) {
		SwingUtilities.invokeLater(new Runnable() {
			// In order to access the balls array only from one thread,
			// let this code be run from the GUI thread.
			public void run() {
				balls.add(b);
				repaint();
			}
		});
	}

	public void mouseClicked(MouseEvent e){
		if(e.getSource().equals(this))
		{
			killBalls.start();
			System.out.println("click !!!");
			
		}
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	public void removeFirstBall(){
		balls.remove(0);
	}
	public boolean isBallsEmpty(){
		return balls.isEmpty();
	}
}

class Killer extends Thread implements Runnable {
	private BallWorld monde;
	public Killer(BallWorld mondeDesBalles){
		monde = mondeDesBalles;
	}
	
	public synchronized void run() {
		while(!monde.isBallsEmpty()) {
			monde.removeFirstBall();
			try { sleep(500); } catch (Exception e) { }
		}
	}
}

