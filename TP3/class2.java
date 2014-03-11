public Class2 implements Semaphore {

    public int nbrjeton =0;

    public Class2 (){
        this(0);
    }

    public Class2 (int nbJeton){
        nbrjeton=nbJeton;
    }

    public void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}
        nbrjeton-=nbJetons;
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notifyAll(); // réveille tous les threads donc plusieurs prendront, si possible, des jetons
    }
}
