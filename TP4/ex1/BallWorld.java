import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

public class BallWorld extends JPanel {

    // default window size
    private final int xSize = 250;
    private final int ySize = 250;
    public Multicast multiCast ;
    public ColorMaster laule;

    private final static Color BGCOLOR = Color.white;

    // a BallWorld contains Ball instances
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    public BallWorld() {
        setPreferredSize(new Dimension(xSize, ySize));
        setOpaque(true);
        setBackground(BGCOLOR);
        multiCast = new Multicast();
        laule = new ColorMaster(this);
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
}


class ColorMaster extends Thread {
    private BallWorld monde;
    public ColorMaster (BallWorld mondeDesBalles){
        monde = mondeDesBalles;
        start();
    }
    public synchronized void run() {
            while(true)
            {
                try { sleep(3000); } catch (Exception e) { }
                int R = (int)(Math.random()*255);
                int G = (int)(Math.random()*255);
                int B = (int)(Math.random()*255);
                Color color = new Color(R, G, B);
                monde.multiCast.Send(color);
                System.err.print("Ca suxxx");
            }


    }
}



