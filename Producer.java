import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<String> queue; //thread safe queue
    String url;

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(url).get();
            String content = doc.text();  //extract all visible text from webpage
            queue.put(content);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

        public Producer(String url, BlockingQueue<String> queue){
            this.url = url;
            this.queue = queue;
        }

}
