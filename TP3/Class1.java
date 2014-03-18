public class Class1 implements Sem {

    public int nbrjeton =0;

    public Class1 (){
        this(0);
    }

    public Class1 (int nbJeton){
        nbrjeton=nbJeton;
    }

    public synchronized void acquire(int nbJetons) throws InterruptedException {
        while(nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}
        nbrjeton-=nbJetons;
    }

    public synchronized void release(int nbJetons) {
        nbrjeton+=nbJetons;
        notify(); // on en réveil qu'un, mais lequel ? mystère ...
    }
}
