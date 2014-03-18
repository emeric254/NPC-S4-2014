public class Class2 implements Sem {

    public int nbrjeton =0;

    public Class2 (){
        this(0);
    }

    public Class2 (int nbJeton){
        nbrjeton=nbJeton;
    }

    public synchronized void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}
        nbrjeton-=nbJetons;
    }

    public synchronized void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notifyAll(); // réveille tous les threads donc plusieurs prendront, si possible, des jetons
    }
}
