import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        List<String> urls = new ArrayList<>();

        System.out.println("Enter up to 3 website URLs (press Enter to skip):");
        for (int i = 1; i <= 3; i++) {
            System.out.print("URL " + i + ": ");
            String url = scanner.nextLine().trim();
            if (!url.isEmpty()) {
                urls.add(url);
            }
        }

        if(urls.isEmpty()){
            System.out.println("Quitting program, no input");
            return; //end program
        }

        System.out.print("Enter a word to search for: ");
        String wordToSearch = scanner.nextLine();

        // Start producer threads (crawlers)
        List<Thread> producerThreads = new ArrayList<>();
        for (String url : urls) {
            Thread producerThread = new Thread(new Producer(url, queue));
            producerThreads.add(producerThread);
            producerThread.start();
        }

        // Start indexer threads (consumers)
        int numConsumers = 3; //can adjust this number as needed
        for (int i = 0; i < numConsumers; i++) {
            Thread consumerThread = new Thread(new Consumer(queue, wordToSearch));
            consumerThread.start();
        }
    }
}
