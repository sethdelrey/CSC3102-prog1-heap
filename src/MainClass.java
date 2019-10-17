import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int maxsize = 100;
        inputFileGenerator(maxsize);
        long startTime = System.nanoTime();

        int k = 4;
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
    }

    public static void inputFileGenerator(int max) {
        try (FileWriter write = new FileWriter(new File("input.txt"))) {
            int[] arr = new int [max];
            int i = 1;
            while (i < arr.length + 1) {
                arr[i-1] = i;
                i++;
            }
            int x = arr[arr.length - 1];
            RandomizeArray(arr);
            i = 0;
            while (i < arr.length) {
                write.write("IN " + arr[i] + "\n");
                i++;
            }

            i = 0;
            while (i < max) {
                write.write("EX\n");
                i++;
            }
            write.close();
        }
        catch (Exception e) {
            System.err.print(e);
        }
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}
