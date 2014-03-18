import java.util.*;

public class Class4 implements Sem {

    public LinkedList<Thread> fifoListe;
    public int nbrjeton =0;

    public Class4 (){
        this(0);
    }

    public Class4 (int nbJeton){
        nbrjeton=nbJeton;
        fifoListe=new LinkedList<Thread>();
    }

    public synchronized void acquire(int nbJetons) throws InterruptedException {

        fifoListe.addLast(Thread.currentThread()); // on ajoute le thread courant en fin de liste
        System.out.println("entrée : "+Thread.currentThread());

        while(fifoListe.getFirst()!= Thread.currentThread() || nbrjeton<nbJetons)
            try { wait(); } catch (Exception e) {}

        nbrjeton-=nbJetons; // on prend un nbr de jeton

        System.out.println("sortie : "+fifoListe.getFirst());

        fifoListe.removeFirst(); // on interrompt la treve de la tete de la liste et on l'enleve
        if(!fifoListe.isEmpty()) fifoListe.getFirst().interrupt(); // sert à rien si plus personne qui attend après
    }

    public synchronized void release(int nbJetons) {
        nbrjeton+=nbJetons; // depot d'un nbr de jetons
        fifoListe.getFirst().interrupt(); // reveil le premier pour qu'il verifie si il peut ou pas sortir
    }
}
