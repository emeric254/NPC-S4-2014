public class Broadcast extends IBroadcast <T> {
    private int laule=0;
    private int currentlaule=0;
    private T objectsended;

    public void Subscribe(){
        laule++;
    }
    public synchronized void Send(T i) throws InterruptedException{
        objectsended=i;
        while(laule>currentlaule) try{sleep(500);}catch(Exception e){}
        notifyAll();
    }
    public synchronized T Receive() throws InterruptedException{
        currentlaule++;
        wait();
        return objectsended;
    }
}
