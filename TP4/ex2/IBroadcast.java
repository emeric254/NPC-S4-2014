public interface IBroadcast <T> {
    public void Subscribe();
    // utilisé pour l'enregistrement d'un thread recepteur
    public void Send(T i) throws InterruptedException;
    public T Receive() throws InterruptedException;
}
