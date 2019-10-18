/*
Raquel Hodgeson (rhodg14@lsu.edu, 89-399-5177)
Seth Richard (sric111@lsu.edu)
Programming Project 1
CSC 3102 - Dr. Shah
10/18/19
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    //Method that prompts user for file name, reads file,
    //and inserts each item in the file into a Heap.
    //It then extracts the minimum values of the Heap according to the
    //input file and prints them in order into a new file.
    //It then provides the running time of the program.
    public static void main(String[] args) {
        System.out.print("Enter the file path of the file you would like to process: ");
        Scanner cin = new Scanner(System.in);
        String filePath = cin.next();
        System.out.print("Enter the maximum number of elements in the heap: ");
        int maxSize = cin.nextInt();
        System.out.print("Enter the number of children a node should have (k): ");
        int k = cin.nextInt();
        long startTime = System.nanoTime();
        MinHeap A = new MinHeap(maxSize, k);
        try (Scanner fin = new Scanner(new File(filePath))) {
            FileWriter write = new FileWriter(new File("output.txt"));
            Scanner in;
            //loop to read each line of input file
            while (fin.hasNextLine()) {
                in = new Scanner(fin.nextLine());
                String choice = in.next();
                if (choice.equals("IN")) {
                    A.insert(in.nextInt());
                }
                else {
                    write.write(A.extractMin() + "\n");
                }
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            write.write(timeElapsed/1000 + " micro-sec\n");
            write.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("There was no file found with that name.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
