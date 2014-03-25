public interface IMulticast <T> {
    public void Send(T data);
    public T Receive() throws InterruptedException ;
}
