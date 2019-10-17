import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int maxsize = 50000;
        int k = 3;
        inputFileGenerator(maxsize);
        MinHeap A = new MinHeap(maxsize, k);
        try (Scanner fin = new Scanner(new File("input.txt"))) {
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
            System.out.println("There was no file found with the name karyHeap-input.txt");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println(timeElapsed/1000+" micro-sec");
    }

    public static void inputFileGenerator(int maxsize) {
        try (FileWriter write = new FileWriter(new File("input.txt"));) {
            int i = 0;
//            while (i < 5000) {
             while (i < maxsize) {
                Random rand = new Random();
                int num = rand.nextInt(20000);
                if (num < 0) {
                    num = num * -1;
                }
                write.write("IN " + num + "\n");
                i++;
            }
            i = 0;
            while (i < maxsize) {
                write.write("EX\n");
                i++;
            }
            write.close();
        }
        catch (Exception e) {
            System.err.print(e);
        }
    }
}
