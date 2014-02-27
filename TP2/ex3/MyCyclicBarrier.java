import java.util.*;
import java.lang.*;
public class MyCyclicBarrier {

    private static volatile int i, all;

    public MyCyclicBarrier(int parties){
        i=0;
        all=parties;
    }

    public void awaitEverybody(){
        i++;
        while(i!=all) try { Thread.currentThread().wait(); } catch (Exception e) {}
        if(i==all)
            reset();
    }

    public void reset(){
        i=0;
        Thread.currentThread().notifyAll();
    }
}
