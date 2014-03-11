import java.util.*;

public Class4 implements Semaphore {

    public Collection<Thread> fifoListe;
    public int nbrjeton =0;

    public Class4 (){
        this(0);
    }

    public Class4 (int nbJeton){
        nbrjeton=nbJeton;
        fifoListe=new LinkedBlockingQueue<Thread>();
    }

    public void acquire(int nbJetons) throws InterruptedException {
        while(!Thread.interrupted())
            try { wait(); } catch (Exception e) {}
// ou :
//        try { wait(); } catch (InterruptedException e) { System.out.println("thread : ",e," Interrompu"); }
        nbrjeton-=nbjeton;
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        fifoListe.put(Thread.currentThread()); // on ajoute le thread courant en fin de liste
        fifoListe.poll().interrupt(); // on interrupt la tete de la liste et on l'enleve
        notifyAll();
    }
}
