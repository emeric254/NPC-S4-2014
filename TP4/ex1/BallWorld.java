import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

public class BallWorld extends JPanel {

    // default window size
    private final int xSize = 250;
    private final int ySize = 250;
    private BarrierKeeper barrier;
    public Class4 barrierSem,barrierKeeperSem;

    private final static Color BGCOLOR = Color.white;

    // a BallWorld contains Ball instances
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    public BallWorld() {
        setPreferredSize(new Dimension(xSize, ySize));
        setOpaque(true);
        setBackground(BGCOLOR);
        barrierSem = new Class4();
        barrierKeeperSem = new Class4();
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

class BarrierKeeper extends Thread {
    private BallWorld monde;
    public BarrierKeeper (BallWorld mondeDesBalles){
        monde = mondeDesBalles;
        start();
    }

    public synchronized void run() {
        while(true) {
            try{ monde.barrierKeeperSem.acquire(4); }catch(Exception e){}
            monde.barrierSem.release(4);
        }
    }
}
