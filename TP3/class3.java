import java.util.*;

public Class3 implements Semaphore {

    public Collection<Thread> fifoListe;
    public int nbrjeton =0;

    public Class3 (){
        this(0);
    }

    public Class3 (int nbJeton){
        nbrjeton=nbJeton;
        fifoListe=new LinkedBlockingQueue<Thread>();
    }

    public void acquire(int nbJetons) throws InterruptedException {
        while(fifoListe.peek()!=Thread.currentThread() && nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}
        nbrjeton-=nbjeton;
        fifoListe.poll(); // on retire le thread courant qui est la tete de la liste
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        fifoListe.put(Thread.currentThread()); // on ajoute le thread courant en fin de liste
        notifyAll();
    }
}
