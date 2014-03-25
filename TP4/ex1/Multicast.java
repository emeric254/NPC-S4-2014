public class Multicast implements IMulticast <Object> {
    private Object temp;
    public int numberofthing=0;
    public synchronized void Send(Object data){
        temp = data;
        if(numberofthing>0)notifyAll();
    }
    public synchronized Object Receive() throws InterruptedException{
        numberofthing++;
        wait();
        numberofthing--;
        return temp;
    }
}
