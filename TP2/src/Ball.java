import java.awt.*;

public class Ball extends Thread implements Runnable {

    private BallWorld world;
    private int xpos, ypos; // Ball coordinates
    private int xinc, yinc; // delta applied when the Ball moves, determines the speed
    private int period;     // period at which the Ball moves
    private boolean varcrit;
    private final Color col;

    private /*final*/ static int ballw = 10;    // width and height of the Ball
    private /*final*/ static int ballh = 10;

    public Ball(BallWorld world, int xpos, int ypos, int xinc, int yinc, int period, Color col) {
        this.world = world;
        this.xpos = xpos;
        this.ypos = ypos;
        this.xinc = xinc;
        this.yinc = yinc;
        this.period = period;
        this.col = col;

        varcrit = true;

        world.addBall(this);
//      run();
    }

    public synchronized void move() {
        //varcrit = true;
        if (xpos >= world.getWidth() - ballw || xpos <= 0)
            xinc = -xinc;

        if (ypos >= world.getHeight() - ballh || ypos <= 0)
            yinc = -yinc;

        //try { Thread.sleep(2); } catch (Exception e) { }

        xpos += xinc;
        ypos += yinc;

        //varcrit=false;

        world.repaint();
    }

    //
    // SYNC: This is called by the GUI
    //
    public synchronized void draw(Graphics g) {
        /*if(varcrit)
        {
            ballh*=2;
            ballw*=2;
        }*/
        g.setColor(col);
        g.fillOval(xpos, ypos, ballw, ballh);
    }

    public void run () {
        while (varcrit) {
            move();
            try { Thread.sleep(period); } catch (Exception e) {  }
        }
    }

    public void setVarCrit(boolean booleen){
        varcrit = booleen;
    }

    public boolean getVarCrit(){
        return varcrit;
    }
}
