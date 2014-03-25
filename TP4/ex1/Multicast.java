public class Multicast implements IMulticast <Object> {
    private Object temp;
    public synchronized void Send(Object data){
        temp = data;
        notifyAll();
    }
    public synchronized Object Receive() throws InterruptedException{
        wait();
        return temp;
    }
}
