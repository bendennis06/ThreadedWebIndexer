import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        List<String> urlList = new ArrayList<String>(); //list to store the urls

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter up to 3 websites or press enter to skip");

        for (int i = 1; i <= 3; i++){
            System.out.println("URL" + i +": ");
            String userInput = scanner.nextLine();
            if(!userInput.isEmpty()){
                urlList.add(userInput);
            }
        }

        System.out.println("Enter a word to search for: ");
        String wordToSearch = scanner.nextLine();

//        System.out.println("Enter the website URL for the first website: ");
//        String userInputURLOne = scanner.nextLine();
//
//        System.out.println("Enter the website URL for the second website: ");
//        String userInputURLTwo = scanner.nextLine();
//
//        System.out.println("Enter the website URL for the third website: ");
//        String userInputURLThree = scanner.nextLine();

        List<Thread> producers = new ArrayList<>(); //list for producers
        for(String url:urlList){
            Thread producer = new Thread(new Producer(url,queue));
            producers.add(producer);
            producer.start();
        }

        Thread consumer = new Thread(new Consumer(queue, wordToSearch));
        consumer.start();



//        Producer producerOne = new Producer(userInputURLOne, queue);
//        Consumer consumerOne = new Consumer(queue, wordToSearch);
//
//        Producer producerTwo = new Producer(userInputURLTwo, queue);
//        Consumer consumerTwo = new Consumer(queue, wordToSearch);
//
//        Producer producerThree = new Producer(userInputURLThree, queue);
//        Consumer consumerThree = new Consumer(queue, wordToSearch);
//
//        Thread producerThreadOne = new Thread(producerOne);
//        Thread consumerThreadOne = new Thread(consumerOne);
//
//        Thread producerThreadTwo = new Thread(producerTwo);
//        Thread consumerThreadTwo = new Thread(consumerTwo);
//
//        Thread producerThreadThree = new Thread(producerThree);
//        Thread consumerThreadThree = new Thread(consumerThree);
//
//        producerThreadOne.start();
//        producerThreadTwo.start();
//        producerThreadThree.start();
//
//        consumerThreadOne.start();
//        consumerThreadTwo.start();
//        consumerThreadThree.start();


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