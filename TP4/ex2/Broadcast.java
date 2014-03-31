public class Broadcast implements IBroadcast <Object> {
    private int total=0;
    private int currentNbr=0;
    private boolean good=false;
    private Object objectsended;

    public synchronized void Subscribe(){
        total++;
    }
    public synchronized void Send(Object i) throws InterruptedException{
        objectsended=i;
        while(total>currentNbr) wait();
        good = true;
        notifyAll();
    }
    public synchronized Object Receive() throws InterruptedException{
        if(total!=0) good=false;
        currentNbr+=1;
        notifyAll();
        while(!good) wait();
        currentNbr-=1;
        return objectsended;
    }
}
