public class Multicast extends IMulticast <Object> {
    Private T temp;
    public void Send(T data){
        temp = data;
        notifyAll();
    }
    public T Receive() throws InterruptedException{
        Thread.currentThread.wait();
        return temp;
    }
}
