import java.util.*;
import java.lang.*;
public class MyCyclicBarrier {

    private static volatile int i, all;

    public MyCyclicBarrier(int parties){
        i=0;
        all=parties;
    }

    public synchronized void awaitEverybody(){
        i++;
        if(i!=all) try { Thread.currentThread().wait(); } catch (Exception e) {}
        else
            reset();
    }

    public synchronized void reset(){
        i=0;
        Thread.currentThread().notifyAll();
    }
}
