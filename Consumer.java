import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//consumer will parse through data

public class Consumer implements Runnable{
    BlockingQueue<String> queue;
    String wordToSearch;

    @Override
    public void run() {
       // BlockingQueue<String> queue = new LinkedBlockingQueue<>(3); //thread safe queue
        try {
            String pageTake = queue.take();
            System.out.println("Test page: " + pageTake.substring(0, 200));
           if (pageTake.contains(wordToSearch)){
               System.out.println(wordToSearch + " is present");
            }else{
               System.out.println(wordToSearch + " is not present.");
           }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Consumer(BlockingQueue<String> queue, String wordToSearch){

        this.queue = queue;
        this.wordToSearch = wordToSearch;
    }
}
