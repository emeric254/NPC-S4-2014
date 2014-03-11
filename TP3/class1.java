public Class1 implements Semaphore {

    public int nbrjeton =0;

    public Class1 (){
        this(0);
    }

    public Class1 (int nbJeton){
        nbrjeton=nbJeton;
    }

    public void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}
        nbrjeton-=nbJetons;
    }

    public void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notify(); // on en réveil qu'un, mais lequel ? mystère ...
    }
}
