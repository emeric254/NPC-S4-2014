import java.util.*;

public Class3 implements Semaphore {

    public LinkedList fifoListe;
    public int nbrjeton =0;

    public void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            wait();
        nbrjeton-=nbjeton;
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notifyAll();
    }
}
