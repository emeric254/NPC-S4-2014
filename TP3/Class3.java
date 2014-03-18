import java.util.*;

public class Class3 implements Sem {

    public LinkedList<Thread> fifoListe;
    public int nbrjeton =0;

    public Class3 (){
        this(0);
    }

    public Class3 (int nbJeton){
        nbrjeton=nbJeton;
        fifoListe=new LinkedList<Thread>();
    }

    public synchronized void acquire(int nbJetons) throws InterruptedException {

        fifoListe.add(Thread.currentThread()); // on ajoute le thread courant en fin de liste

        while(fifoListe.getFirst()!= Thread.currentThread() || nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}

        nbrjeton-=nbJetons;

        fifoListe.removeFirst(); // on retire le thread courant qui est la tete de la liste

        if(nbrjeton>0 && !fifoListe.isEmpty()) notifyAll();
    }

    public synchronized void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notifyAll();
    }
}
