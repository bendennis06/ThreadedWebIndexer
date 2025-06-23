import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//consumer will parse through data

public class Consumer implements Runnable{
    BlockingQueue<String> queue;
    String wordToSearch;
    Boolean found = false;

    @Override
    public void run() {
       // BlockingQueue<String> queue = new LinkedBlockingQueue<>(3); //thread safe queue
        try {
            String pageTake = queue.take();
            String [] pageText = pageTake.split(" ");

           // System.out.println("Test page: " + pageTake.substring(0, 200));
            for(int i = 0; i < pageText.length - 1;i++){
                if(pageText[i].equalsIgnoreCase(wordToSearch)) {
                    found = true;
                    int startIndex = i - 10;
                    if (startIndex < 0) {
                        startIndex = 0;
                    }
                    int endIndex = i + 10;
                    if (endIndex > pageText.length) {
                        endIndex = pageText.length;
                    }

                    for (int j = startIndex; j < endIndex; j++) {
                        System.out.print(pageText[j] + " ");
                    }
                }
            }
            if(!found){
                System.out.println("not found");
                System.exit(0);
            }

//           if (pageTake.contains(wordToSearch)){
//               System.out.println(wordToSearch + " is present");
//
//
//            }else{
//               System.out.println(wordToSearch + " is not present.");
//           }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Consumer(BlockingQueue<String> queue, String wordToSearch){

        this.queue = queue;
        this.wordToSearch = wordToSearch;
    }
}
