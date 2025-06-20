import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
    Producer producer = new Producer("https://www.fmscout.com/a-football-manager-2024-wonderkids.html", queue);
    Consumer consumer = new Consumer(queue);

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();
    
        }
    }


    /*You start 3 crawler threads:
  - Crawler 1 downloads https://site1.com
  - Crawler 2 downloads https://site2.com
  - Crawler 3 downloads https://site3.com

They all put their results into the shared queue.

Then, 2 indexer threads:
  - Indexer 1 pulls page from the queue and parses it
  - Indexer 2 pulls the next one and parses that
*/