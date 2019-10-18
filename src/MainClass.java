import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MainClass {

    //Method that prompts user for file name, reads file,
    //and inserts each item in the file into a Heap.
    //It then extracts the minimum values of the Heap according to the
    //input file and prints them in order into a new file.
    //It then provides the running time of the program.
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int maxsize = 50000;     //maximum size of Heap - change as needed
        int k = 3;              //maximum number of children each node in Heap can have - change as needed
        MinHeap A = new MinHeap(maxsize, k);
        try (Scanner fin = new Scanner(new File("karyHeap-input.txt"))) {
            FileWriter write = new FileWriter(new File("output.txt"));
            Scanner in;
            int i = 0;
            while (fin.hasNextLine()) {
                in = new Scanner(fin.nextLine());
                String choice = in.next();
                if (choice.equals("IN")) {
                    A.insert(in.nextInt());
                }
                else {
                    i++;
                    write.write(A.extractMin() + "\n");
                }
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            write.write(timeElapsed/1000 + " micro-sec\n");
            write.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("There was no file found with the name karyHeap-input.txt");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

