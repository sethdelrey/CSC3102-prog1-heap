import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {
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
                    write.write(A.extractMin() + "\n"); //"\\ result for extract-min operation " + i+"\n");
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
//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println(timeElapsed/1000+" micro-sec");
    }

}
