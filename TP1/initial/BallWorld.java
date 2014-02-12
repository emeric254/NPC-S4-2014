import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class BallWorld extends JPanel implements ActionListener {

	// default window size
	private final int xSize = 250;
	private final int ySize = 250;

	private final static Color BGCOLOR = Color.white;

	// a BallWorld contains Ball instances 
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	public BallWorld() {
		setPreferredSize(new Dimension(xSize, ySize));
		setOpaque(true);
		setBackground(BGCOLOR);
//		this.addActionListener(this);
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

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this))
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					killBall();
				}
			});
		}
	}
	
	public void killBall() {
		try { Thread.sleep(500+(int)Math.random()); } catch (Exception e) { }
		if(!balls.isEmpty())
			balls.remove(0);
	}
}
