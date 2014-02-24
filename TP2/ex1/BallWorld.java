import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

public class BallWorld extends JPanel {

    // default window size
    private final int xSize = 250;
    private final int ySize = 250;
    private BarrierKeeper barrier;
    public Semaphore barrierSem,barrierKeeperSem;

    private final static Color BGCOLOR = Color.white;

    // a BallWorld contains Ball instances
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    public BallWorld() {
        setPreferredSize(new Dimension(xSize, ySize));
        setOpaque(true);
        setBackground(BGCOLOR);
        barrierSem = new Semaphore(0,true);
        barrierKeeperSem = new Semaphore(0,true);
        barrier = new BarrierKeeper(this);
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

class BarrierKeeper extends Thread implements Runnable {
    private BallWorld monde;
    public BarrierKeeper (BallWorld mondeDesBalles){
        monde = mondeDesBalles;
        start();
    }

    public synchronized void run() {
        while(true) {
            if(monde.barrierKeeperSem.tryAcquire(4))
                monde.barrierSem.release(4);
        }
    }
}
