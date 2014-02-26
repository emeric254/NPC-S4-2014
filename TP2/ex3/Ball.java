import java.awt.*;

public class Ball extends Thread implements Runnable {

    private BallWorld world;
    private int xpos, ypos; // Ball coordinates
    private int xinc, yinc; // delta applied when the Ball moves, determines the speed
    private int period;     // period at which the Ball moves
    private final Color col;

    private static int ballw = 10;    // width and height of the Ball
    private static int ballh = 10;

    public Ball(BallWorld world, int xpos, int ypos, int xinc, int yinc, int period, Color col) {
        this.world = world;
        this.xpos = xpos;
        this.ypos = ypos;
        this.xinc = xinc;
        this.yinc = yinc;
        this.period = period;
        this.col = col;

        world.addBall(this);
    }

    public void move() {
        synchronized (this){
            if (xpos >= world.getWidth() - ballw || xpos <= 0)
                xinc = -xinc;

            if (ypos >= world.getHeight() - ballh || ypos <= 0)
                yinc = -yinc;

            xpos += xinc;
            ypos += yinc;
        }

        if(Math.abs(xpos-ypos)<2) {
            try{world.barrier.awaitEverybody();} catch (Exception e) {}
        }

        world.repaint();
    }

    //
    // SYNC: This is called by the GUI
    //
    public synchronized void draw(Graphics g) {
        g.setColor(col);
        g.fillOval(xpos, ypos, ballw, ballh);
    }

    public void run () {
        while (true) {
            move();
            try { Thread.sleep(period); } catch (Exception e) {  }
        }
    }
}
