public Class1 implements Semaphore {

    public int nbrjeton =0;

    public void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            wait();
        nbrjeton-=nbJetons;
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notify();
    }
}
