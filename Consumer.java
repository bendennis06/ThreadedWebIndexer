import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable{
    BlockingQueue<String> queue;

    @Override
    public void run() {
       // BlockingQueue<String> queue = new LinkedBlockingQueue<>(3); //thread safe queue
        try {
            String pageTake = queue.take();
            System.out.println("Test page: " + pageTake.substring(0, 200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }
}
