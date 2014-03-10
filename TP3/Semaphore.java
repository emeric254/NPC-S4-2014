public interface Semaphore {
    public void acquire(int nbJetons) throws InterruptedException;
    public void release(int nbJetons);
}
