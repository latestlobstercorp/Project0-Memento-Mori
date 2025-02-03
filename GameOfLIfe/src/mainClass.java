import java.io.*;
import java.util.Scanner;

public class mainClass {
    public class Main {
        public static void main(String[] args) {
            Scanner scnr = new Scanner(System.in);
    
            System.out.print("Please enter a valid file name: ");
            File inFile = new File(scnr.nextLine());
    
    
            GameOfLife rizzy = new GameOfLife(inFile);
    
            System.out.print("How many generations to compute: ");
            int generations = scnr.nextInt();
    
            rizzy.computeNextGeneration(generations);
        }
    }  
}
