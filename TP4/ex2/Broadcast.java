public class Broadcast implements IBroadcast <Object> {
    private int laule=0;
    private int currentlaule=0;
    private Object objectsended;

    public void Subscribe(){
        laule++;
    }
    public void Send(Object i) throws InterruptedException{
        objectsended=i;
        while(laule>currentlaule)
        {
            //~ try{Thread.sleep(500);}catch(Exception e){}
            System.err.println("currentlaule : " + currentlaule );
            System.err.println("laule : " + laule );
        }
        notifyAll();
        currentlaule=0;

    }
    public synchronized Object Receive() throws InterruptedException{
        currentlaule+=1;
        wait();
        return objectsended;
    }
}
