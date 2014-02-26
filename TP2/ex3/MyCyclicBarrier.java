import java.util.*;
import java.lang.*;
public class MyCyclicBarrier {

    private static volatile int i, all;

    public MyCyclicBarrier(int parties){
        all=parties;
        i=0;
    }

    public void awaitEverybody(){
        i++;
        if(i==all)
            reset();
        else
            try { Thread.currentThread().wait(); } catch (Exception e) {}
    }

    public void reset(){
        i=0;
        Thread.currentThread().notifyAll();
    }
}
