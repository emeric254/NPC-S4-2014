public class Multicast implements IMulticast <Object> {
    private Object temp;
    public synchronized void Send(Object data){
        temp = data;
        notifyAll();
    }
    public Object Receive() throws InterruptedException{
        System.err.println("lol");
        try {Thread.currentThread().wait();}catch(Exception e){e.printStackTrace();}
        System.err.println("lol2");
        return (Object)temp;
    }
}
